package fi.sasu.hackernewapp.service

import android.app.Application
import android.graphics.ColorSpace
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseConfiguration
import com.couchbase.lite.MutableDocument
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.itemObject.Model
import fi.sasu.hackernewapp.method.RequestApi
import java.io.IOException


open class MyApplication : Application() {
        var topstoriesInstant: MutableSet<String> = HashSet()
        var newstoriesInstant: MutableSet<String> = HashSet()
        var showstoriesInstant: MutableSet<String> = HashSet()
        var askstoriesInstant: MutableSet<String> = HashSet()
        var jobstoriesInstant: MutableSet<String> = HashSet()
        var itemObjecResponse:Model? =null
    val requestApi= RequestApi()
        var topstoriesListInstant = ArrayList<Model>()
        var newstoriesListInstant = ArrayList<Model>()
        var showstorieListsInstant = ArrayList<Model>()
        var askstoriesListInstant = ArrayList<Model>()
        var jobstoriesListInstant = ArrayList<Model>()


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
