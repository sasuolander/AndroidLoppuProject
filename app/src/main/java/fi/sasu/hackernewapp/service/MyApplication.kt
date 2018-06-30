package fi.sasu.hackernewapp.service

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseConfiguration
import com.couchbase.lite.MutableDocument
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.itemObject.Model
import java.io.IOException

open class MyApplication : Application() {
    val topstoriesInstant: MutableSet<String> = HashSet()
    val topstoriesListInstant = ArrayList<Model>()
    val newstoriesInstant: MutableSet<String> = HashSet()
    val newstoriesListInstant = ArrayList<Model>()
    val showstoriesInstant: MutableSet<String> = HashSet()
    val showstoriesListInstant = ArrayList<Model>()
    val askstoriesInstant: MutableSet<String> = HashSet()
    val askstoriesListInstant = ArrayList<Model>()
    val jobstoriesInstant: MutableSet<String> = HashSet()
    val jobstoriesListInstant = ArrayList<Model>()
    var itemObjecResponse:Model? =null
    lateinit var database:Database
    val documentItem: MutableDocument=MutableDocument().setFloat("version", 2.0F)
            .setString("type", "SDK")
    val helperclass:helperclass= helperclass()
    override fun onCreate() {
        super.onCreate()
        instance = this
        topstoriesInstant.size
        try {
            val config = DatabaseConfiguration(applicationContext)
            database = Database("mydb", config)
        }
        catch (e:IOException) {
            helperclass.naytaToast(e.toString(),applicationContext)
        }
        database.save(documentItem)
    }
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
}
