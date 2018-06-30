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
        MyApplication.instance?.addToRequestQueue(itemJSONObject, "json") }

    fun loadItems(name:String){
        when (name){
            "topstories"->{
                Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.topstoriesInstant!!.isNotEmpty()){
                    iterate(MyApplication.instance?.topstoriesInstant!!,
                            MyApplication.instance?.topstoriesListInstant!!)
                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                    Log.d(helper.userNameForLogging,
                            MyApplication.instance?.topstoriesInstant.toString())
                } }
            "newstories"->{
                Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.newstoriesInstant!!.isNotEmpty()){
                    iterate(MyApplication.instance?.newstoriesInstant!!,
                            MyApplication.instance?.newstoriesListInstant!!)
                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                    Log.d(helper.userNameForLogging,
                            MyApplication.instance?.newstoriesInstant.toString())
                } }
            "showstories"->{
                Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.showstoriesInstant!!.isNotEmpty()){
                    iterate(MyApplication.instance?.showstoriesInstant!!,
                            MyApplication.instance?.showstoriesListInstant!!)
                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                    Log.d(helper.userNameForLogging,
                            MyApplication.instance?.showstoriesInstant.toString())
                } }
            "askstories"->{Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.askstoriesInstant!!.isNotEmpty()){
                    iterate(MyApplication.instance?.askstoriesInstant!!,
                            MyApplication.instance?.askstoriesListInstant!!)

                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                    Log.d(helper.userNameForLogging,
                            MyApplication.instance?.askstoriesInstant.toString())
                } }
            "jobstories"->{Log.d(helper.userNameForLogging,"start")
                if(MyApplication.instance?.jobstoriesInstant!!.isNotEmpty()){
                    iterate(MyApplication.instance?.jobstoriesInstant!!,
                            MyApplication.instance?.jobstoriesListInstant!!)
                }else{
                    Log.d(helper.userNameForLogging,"list is empty")
                    Log.d(helper.userNameForLogging,
                            MyApplication.instance?.jobstoriesInstant.toString())
                } }
            else ->Log.d(helper.userNameForLogging,"wrong list")
        } }

    fun topstories() {
        val  topstoriesJsonArrayRequest =
                JsonArrayRequest(Request.Method.GET,topstories,null,
                        Response.Listener<JSONArray> { response ->
                            MyApplication.instance?.topstoriesInstant = convert(response)
                            Log.d("topstories",
                                    MyApplication.instance?.topstoriesInstant.toString())
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
                        Response.ErrorListener { error ->
                            Log.d(helper.userNameForLogging,error.toString()) })
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
                            ) },
                        Response.ErrorListener { error ->
                            Log.d(helper.userNameForLogging,error.toString()) })
        MyApplication.instance?.addToRequestQueue(jobstoriesJsonArrayRequest, "json") }

    private fun convertObject (response: JSONObject): Model {
        val gson = Gson()
        val output:Model =gson.fromJson<Model>(response.toString() , Model::class.java)
        return output }

    private fun convert (response: JSONArray):MutableSet<String> {
        val gson = Gson()
        val listType = object : TypeToken<MutableSet<String>>() {}.type
        return gson.fromJson<MutableSet<String>>(response.toString(), listType)}

    private fun iterate(idList:MutableSet<String>,output:ArrayList<Model>) {
        Log.d("list is not empty",idList.size.toString())
        val listForRequest=idList.toList().subList(0,maxCount)
        Log.d("list for request",listForRequest.size.toString())
        for (id in listForRequest){
            val jsonID="$id.json"
            val itemJSONObject =JsonObjectRequest(Request.Method.GET,
                    itemURl+jsonID,null,
                    Response.Listener<JSONObject> { response ->
                        output.add(convertObject(response)) },
                    Response.ErrorListener { error ->
                        Log.d(helper.userNameForLogging,error.toString()) })
            MyApplication.instance?.addToRequestQueue(itemJSONObject, "json") } }
}