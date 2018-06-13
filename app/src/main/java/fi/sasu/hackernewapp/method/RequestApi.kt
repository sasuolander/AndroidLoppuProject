package fi.sasu.hackernewapp.method

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import fi.sasu.hackernewapp.activity.MainActivity
import fi.sasu.hackernewapp.service.BackGroundTask
import fi.sasu.hackernewapp.service.MySingleton
import org.json.JSONArray
import java.security.AccessController.getContext

 class RequestApi  {
    val baseURL:String = "https://hacker-news.firebaseio.com/v0/"
    val itemURl:String =baseURL+"item/"
    val topstories:String =baseURL+"topstories.json"
    val newstories:String =baseURL+"newstories.json"
    val showstories:String =baseURL+"showstories.json"
    val askstories:String =baseURL+"askstories.json"
    val jobstories:String =baseURL+"jobstories.json"
    // Instantiate the RequestQueue
}