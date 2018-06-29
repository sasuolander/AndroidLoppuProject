package fi.sasu.hackernewapp.method

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.couchbase.lite.Database
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import fi.sasu.hackernewapp.itemObject.Model
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.service.MyApplication
import org.json.JSONArray
import org.json.JSONObject

class RequestApi  {
    private val helper:helperclass= helperclass()

    private val baseURL:String = "https://hacker-news.firebaseio.com/v0/"
    private val itemURl:String =baseURL+"item/"
    private val topstories:String =baseURL+"topstories.json"
    private val newstories:String =baseURL+"newstories.json"
    private val showstories:String =baseURL+"showstories.json"
    private val askstories:String =baseURL+"askstories.json"
    private val jobstories:String =baseURL+"jobstories.json"
    private val maxCount=10

    private val db:Database?=MyApplication.instance?.database

    fun itemobjectOne(id:Int){
        val jsonID="$id.json"
        val itemJSONObject =JsonObjectRequest(Request.Method.GET,itemURl+jsonID,
                null,
                Response.Listener<JSONObject> { response ->
                    //response is working
                    MyApplication.instance?.itemObjecResponse=convertObject(response)
                    Log.d(helper.userNameForLogging,MyApplication.instance?.
                            itemObjecResponse.toString()) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(itemJSONObject, "json")
    }

    fun loadItems(name:String){
        when (name){
            "topstories"->{
                Log.d(helper.userNameForLogging,"start")
                var counter = 0
                //while (counter<=maxCount){
                    //counter++
                topstories()
                    if(MyApplication.instance?.topstoriesInstant!!.isNotEmpty()){
                        val idList = MyApplication.instance?.topstoriesInstant
                        Log.d("list is not empty",idList.toString())
                        for (id in idList!!){
                            val jsonID="$id.json"
                            val itemJSONObject =JsonObjectRequest(Request.Method.GET,
                                    itemURl+jsonID,null,
                                    Response.Listener<JSONObject> { response ->
                                        MyApplication.instance?.topstoriesListInstant!!.
                                                add(convertObject(response))
                                    },
                                    Response.ErrorListener { error ->
                                        Log.d(helper.userNameForLogging,error.toString()) })
                            MyApplication.instance?.addToRequestQueue(itemJSONObject, "json")
                        }
                    }else{
                        Log.d(helper.userNameForLogging,"list is empty")
                        Log.d(helper.userNameForLogging,MyApplication.instance?.topstoriesInstant.toString())
                    }
                //}


            }
            "newstories"->{
                Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.newstoriesInstant!==null){
                    val idList = MyApplication.instance?.newstoriesInstant
                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                }
            }
            "showstories"->{
                Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.showstoriesInstant!==null){
                    val idList =MyApplication.instance?.showstoriesInstant
                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                }
            }
            "askstories"->{Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.askstoriesInstant!==null){
                    val idList =MyApplication.instance?.askstoriesInstant

                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                }
            }
            "jobstories"->{Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.jobstoriesInstant!==null){
                    val idList =MyApplication.instance?.jobstoriesInstant

                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                }
            }
            else ->Log.d(helper.userNameForLogging,"wrong list")

        }
    }






    fun topstories() {
       val  topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,topstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.topstoriesInstant = convert(response)
                Log.d("topstories",MyApplication.instance?.topstoriesInstant.toString())
                },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json") }

    fun newstories() {
       val newstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,newstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.newstoriesInstant = convert(response) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(newstoriesJsonArrayRequest, "json") }

    fun showstories() {
        val showstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,showstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.showstoriesInstant = convert(response) },
                Response.ErrorListener { error -> Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(showstoriesJsonArrayRequest, "json") }

    fun askstories() {
       val askstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,askstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.askstoriesInstant = convert(response) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(askstoriesJsonArrayRequest, "json") }

    fun jobstories() {
        val jobstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,jobstories,null,
                Response.Listener<JSONArray> { response ->
                    val setter =MyApplication.instance?.jobstoriesInstant
                    setter?.addAll(convert(response)
                    )
                     },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(jobstoriesJsonArrayRequest, "json") }


    private fun convertObject (response: JSONObject): Model {
        //get data from gson and assign to object
        val gson = Gson()
        val output:Model =gson.fromJson<Model>(response.toString() , Model::class.java)

        return output }

    private fun convert (response: JSONArray):MutableSet<String> {
        //get data from gson and assign to object
        val gson = Gson()
        val listType = object : TypeToken<MutableSet<String>>() {}.type
        return gson.fromJson<MutableSet<String>>(response.toString(), listType)}
}