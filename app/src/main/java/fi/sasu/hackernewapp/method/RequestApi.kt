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
    private val gson = Gson()
    private val baseURL:String = "https://hacker-news.firebaseio.com/v0/"
    private val itemURl:String =baseURL+"item/"
    private val topstories:String =baseURL+"topstories.json"
    private val newstories:String =baseURL+"newstories.json"
    private val showstories:String =baseURL+"showstories.json"
    private val askstories:String =baseURL+"askstories.json"
    private val jobstories:String =baseURL+"jobstories.json"


    private val db:Database?=MyApplication.instance?.database

    fun itemobject(id:Int):Model?{
        val jsonID="$id.json"
        //lateinit var itemObjecResponse:Model
        val itemJSONObject =JsonObjectRequest(Request.Method.GET,itemURl+jsonID,null,
                Response.Listener<JSONObject> { response ->
                MyApplication.instance?.itemObjecResponse= convertObject(response) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(itemJSONObject, "json")
        return MyApplication.instance?.itemObjecResponse
        //try cacth
    }
    fun topstories() {

       val  topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,topstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.topstoriesInstant = convert(response) },
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
        val output =gson.fromJson<Model>(response.toString() , Model::class.java)
        output.kidsObject
        return output }

    private fun convert (response: JSONArray):MutableSet<String> {
        //get data from gson and assign to object
        val listType = object : TypeToken<MutableSet<String>>() {}.type
        return gson.fromJson<MutableSet<String>>(response.toString(), listType)}
}