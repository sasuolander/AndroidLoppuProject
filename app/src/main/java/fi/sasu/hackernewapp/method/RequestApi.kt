package fi.sasu.hackernewapp.method

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.service.MyApplication
import org.json.JSONArray

class RequestApi  {

    val helper:helperclass= helperclass()

    val baseURL:String = "https://hacker-news.firebaseio.com/v0/"
    val itemURl:String =baseURL+"item/"
    val topstories:String =baseURL+"topstories.json"
    val newstories:String =baseURL+"newstories.json"
    val showstories:String =baseURL+"showstories.json"
    val askstories:String =baseURL+"askstories.json"
    val jobstories:String =baseURL+"jobstories.json"
    // Instantiate the RequestQueue


    private fun topstories() {
        val topstoriesJsonArrayRequest = JsonArrayRequest(Request.Method.GET,topstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.topstoriesInstant = convert(response)
                },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString())
                }
        )
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json")
        //queue.start()
    }

    private fun newstories() {
        val topstoriesJsonArrayRequest = JsonArrayRequest(Request.Method.GET,newstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.newstoriesInstant = convert(response)
                },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString())
                }
        )
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json")
        //queue.start()
    }
    private fun showstories() {
        val topstoriesJsonArrayRequest = JsonArrayRequest(Request.Method.GET,showstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.showstoriesInstant = convert(response)
                },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString())
                }
        )
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json")
        //queue.start()
    }
    private fun askstories() {
        val topstoriesJsonArrayRequest = JsonArrayRequest(Request.Method.GET,askstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.askstoriesInstant = convert(response)
                },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString())
                }
        )
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json")
        //queue.start()
    }
    private fun jobstories() {
        val topstoriesJsonArrayRequest = JsonArrayRequest(Request.Method.GET,jobstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.jobstoriesInstant = convert(response)
                },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString())
                }
        )
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json")
        //queue.start()
    }

    private fun convert (response: JSONArray):List<String> {
        //initialize gson object
        val gson = Gson()
        //get data from gson and assign to object
        val listType = object : TypeToken<List<String>>() {}.type
        val idList = gson.fromJson<List<String>>(response.toString(), listType)

        return idList
        //set layout manager for recycler view
        //set adapter for recycler view
    }

}