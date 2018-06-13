package fi.sasu.hackernewapp.service

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.*
import com.android.volley.toolbox.ImageLoader
import android.app.ActivityManager
import com.android.volley.toolbox.Volley



class MySingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null
                }

    private var mRequestQueue: RequestQueue? = null


    private fun MySingleton() {
        // no instances
    }


    fun init(context: Context) {
        mRequestQueue = Volley.newRequestQueue(context)

        val memClass = (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .memoryClass

    }



    val imageLoader: ImageLoader by lazy {
        ImageLoader(requestQueue,
                object : ImageLoader.ImageCache {
                    private val cache = LruCache<String, Bitmap>(20)
                    override fun getBitmap(url: String): Bitmap {
                        return cache.get(url)
                    }
                    override fun putBitmap(url: String, bitmap: Bitmap) {
                        cache.put(url, bitmap)
                    }
                })
    }
    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}
