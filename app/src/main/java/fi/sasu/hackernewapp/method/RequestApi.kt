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
    val helper:helperclass= helperclass()
    val baseURL:String = "https://hacker-news.firebaseio.com/v0/"
    val itemURl:String =baseURL+"item/"
    val topstories:String =baseURL+"topstories.json"
    val newstories:String =baseURL+"newstories.json"
    val showstories:String =baseURL+"showstories.json"
    val askstories:String =baseURL+"askstories.json"
    val jobstories:String =baseURL+"jobstories.json"



    val db:Database?=MyApplication.instance?.database

    fun itemobject(id:Int):Model?{
        val jsonID="$id.json"
        var itemobjecResponse:Model?=null
        val itemJSONObject =JsonObjectRequest(Request.Method.GET,itemURl+jsonID,null,
                Response.Listener<JSONObject> { response ->


                itemobjecResponse= convertObject(response) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(itemJSONObject, "json")
        return itemobjecResponse
    }
    fun topstories() {

        val topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,topstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.topstoriesInstant = convert(response) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json") }

    fun newstories() {
        val topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,newstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.newstoriesInstant = convert(response) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json") }

    fun showstories() {
        val topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,showstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.showstoriesInstant = convert(response) },
                Response.ErrorListener { error -> Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json") }

    fun askstories() {
        val topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,askstories,null,
                Response.Listener<JSONArray> { response ->
                    MyApplication.instance?.askstoriesInstant = convert(response) },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json") }

    fun jobstories() {
        val topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,jobstories,null,
                Response.Listener<JSONArray> { response ->
                    val setter =MyApplication.instance?.jobstoriesInstant
                    setter?.addAll(convert(response)
                    )
                     },
                Response.ErrorListener { error ->
                    Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(topstoriesJsonArrayRequest, "json") }


    private fun convertObject (response: JSONObject): Model {
        //initialize gson object
        val gson = Gson()
        //get data from gson and assign to object
        val output =gson.fromJson<Model>(response.toString() , Model::class.java)
        output.kidsObject
        return output }

    private fun convert (response: JSONArray):MutableSet<String> {
        //initialize gson object
        val gson = Gson()
        //get data from gson and assign to object
        val listType = object : TypeToken<MutableSet<String>>() {}.type
        return gson.fromJson<MutableSet<String>>(response.toString(), listType)}
}