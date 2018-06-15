package fi.sasu.hackernewapp.service

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.StrictMode
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.couchbase.lite.Attachment
import com.couchbase.lite.CouchbaseLiteException
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseOptions
import com.couchbase.lite.Document
import com.couchbase.lite.LiveQuery
import com.couchbase.lite.Manager
import com.couchbase.lite.Query
import com.couchbase.lite.QueryEnumerator
import com.couchbase.lite.QueryRow
import com.couchbase.lite.SavedRevision
import com.couchbase.lite.TransactionalTask
import com.couchbase.lite.UnsavedRevision
import com.couchbase.lite.android.AndroidContext
import com.couchbase.lite.auth.Authenticator
import com.couchbase.lite.auth.AuthenticatorFactory
import com.couchbase.lite.replicator.Replication
import com.couchbase.lite.util.ZipUtils
import com.facebook.stetho.Stetho
import com.robotpajamas.stetho.couchbase.CouchbaseInspectorModulesProvider

import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map



open class MyApplication : Application() {

        override fun onCreate() {
            super.onCreate()
            instance = this

            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            if (BuildConfig.DEBUG) {
                Stetho.initialize(
                        Stetho.newInitializerBuilder(this)
                                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                                .enableWebKitInspector(CouchbaseInspectorModulesProvider(this))
                                .build())
            }

            if (mLoginFlowEnabled) {
                login()
            } else {
                startSession("todo", null, null)
            }

            try {
                manager = Manager(AndroidContext(applicationContext), Manager.DEFAULT_OPTIONS)
            } catch (e: IOException) {
                e.printStackTrace()
            }


        }

    private val mLoginFlowEnabled = false
    private val mEncryptionEnabled = false
    private val mSyncEnabled = false
    private val mSyncGatewayUrl = "http://10.0.2.2:4984/hackerapp/"
    private val mLoggingEnabled = false
    private val mUsePrebuiltDb = false
    private val mConflictResolution = false

    fun getDatabase(): Database? {
        return database
    }

    private var database: Database? = null
    private var pusher: Replication? = null
    private var puller: Replication? = null
    private val accessDocuments = ArrayList<Document>()

    private var mUsername: String? = null

    var topstoriesInstant: MutableSet<String> = HashSet()
    var newstoriesInstant: MutableSet<String> = HashSet()
    var showstoriesInstant: MutableSet<String> = HashSet()
    var askstoriesInstant: MutableSet<String> = HashSet()
    var jobstoriesInstant: MutableSet<String> = HashSet()

    val requestQueue: RequestQueue? = null
            get() {
                if (field == null) {
                    return Volley.newRequestQueue(applicationContext)
                }
                return field
            }

        fun <T> addToRequestQueue(request: Request<T>, tag: String) {
            request.tag = if (TextUtils.isEmpty(tag)) TAG else tag
            requestQueue?.add(request)
        }

        fun <T> addToRequestQueue(request: Request<T>) {
            request.tag = TAG
            requestQueue?.add(request)
        }

        fun cancelPendingRequests(tag: Any) {
            if (requestQueue != null) {
                requestQueue!!.cancelAll(tag)
            }
        }

        companion object {
            private val TAG = MyApplication::class.java.simpleName
            @get:Synchronized var instance: MyApplication? = null
                private set
        }


    private fun enableLogging() {
        Manager.enableLogging(TAG, Log.VERBOSE)
        Manager.enableLogging(com.couchbase.lite.util.Log.TAG, Log.VERBOSE)
        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC_ASYNC_TASK, Log.VERBOSE)
        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC, Log.VERBOSE)
        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_QUERY, Log.VERBOSE)
        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_VIEW, Log.VERBOSE)
        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_DATABASE, Log.VERBOSE)
    }

    // Session

    private fun startSession(username: String, password: String?, newPassword: String?) {
        enableLogging()
        installPrebuiltDb()
        openDatabase(username, password, newPassword)
        mUsername = username


    }

    private fun installPrebuiltDb() {
        if (!mUsePrebuiltDb) {
            return
        }

        try {
            manager = Manager(AndroidContext(applicationContext), Manager.DEFAULT_OPTIONS)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            database = manager.getExistingDatabase("todo")
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
        }

        if (database == null) {
            try {
                ZipUtils.unzip(assets.open("todo.zip"), manager.getContext().getFilesDir())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun openDatabase(username: String, key: String?, newKey: String?) {
        val dbname = username
        val options = DatabaseOptions()
        options.setCreate(true)

        if (mEncryptionEnabled) {
            options.setEncryptionKey(key)
        }

        var manager: Manager? = null
        try {
            manager = Manager(AndroidContext(applicationContext), Manager.DEFAULT_OPTIONS)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            database = manager!!.openDatabase(dbname, options)
        } catch (e: CouchbaseLiteException) {
            e.printStackTrace()
        }

        if (newKey != null) {
            try {
                database.changeEncryptionKey(newKey)
            } catch (e: CouchbaseLiteException) {
                e.printStackTrace()
            }

        }

        database.addChangeListener(object : Database.ChangeListener() {
            fun changed(event: Database.ChangeEvent) {
                if (!event.isExternal()) {
                    return
                }

                for (change in event.getChanges()) {
                    if (!change.isCurrentRevision()) {
                        continue
                    }

                    val changedDoc = database.getExistingDocument(change.getDocumentId()) ?: return

                    val docType = changedDoc!!.getProperty("type") as String
                    if (docType != "task-list.user") {
                        continue
                    }

                    val username = changedDoc!!.getProperty("username") as String
                    if (username != getUsername()) {
                        continue
                    }

                    accessDocuments.add(changedDoc)
                    changedDoc!!.addChangeListener(object : Document.ChangeListener() {
                        fun changed(event: Document.ChangeEvent) {
                            val changedDoc = database.getDocument(event.getChange().getDocumentId())
                            if (!changedDoc.isDeleted()) {
                                return
                            }

                            try {
                                val deletedRev = changedDoc.getLeafRevisions().get(0)
                                val listId = deletedRev.getProperty("taskList")["id"] as String
                                val listDoc = database.getExistingDocument(listId)
                                accessDocuments.remove(changedDoc)
                                listDoc.purge()
                                changedDoc.purge()
                            } catch (e: CouchbaseLiteException) {
                                Log.e(TAG, "Failed to get deleted rev in document change listener")
                            }

                        }
                    })
                }
            }
        })
    }

    private fun closeDatabase() {
        // TODO: stop conflicts live query
        database.cloce
    }

    // Login





    fun login(username: String, password: String) {
        mUsername = username
        startSession(username, password, null)
    }

    fun logout() {
        runOnUiThread(Runnable {
            stopReplication()
            closeDatabase()
            login()
        })
    }





    fun showErrorMessage(errorMessage: String, throwable: Throwable) {
        runOnUiThread(Runnable {
            android.util.Log.e(TAG, errorMessage, throwable)
            val msg = String.format("%s",
                    errorMessage)
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
        })
    }

    private fun runOnUiThread(runnable: Runnable) {
        val mainHandler = Handler(applicationContext.mainLooper)
        mainHandler.post(runnable)
    }

    private fun startConflictLiveQuery() {
        if (!mConflictResolution) {
            return
        }

        val conflictsLiveQuery = database.createAllDocumentsQuery().toLiveQuery()
        conflictsLiveQuery.setAllDocsMode(Query.AllDocsMode.ONLY_CONFLICTS)
        conflictsLiveQuery.addChangeListener(object : LiveQuery.ChangeListener() {
            fun changed(event: LiveQuery.ChangeEvent) {
                resolveConflicts(event.getRows())
            }
        })
        conflictsLiveQuery.start()
    }
    }
