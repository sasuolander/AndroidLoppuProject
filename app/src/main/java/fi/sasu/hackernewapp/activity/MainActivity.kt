package fi.sasu.hackernewapp.activity


import android.content.Intent
import android.graphics.ColorSpace
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.android.volley.Request
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.method.RequestApi
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import fi.sasu.hackernewapp.`object`.ID_List
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.service.MyApplication
import org.json.JSONArray
import org.json.JSONObject
import com.google.gson.reflect.TypeToken
import java.util.stream.Collectors.mapping
import com.google.gson.JsonElement




class MainActivity : AppCompatActivity() {



    var helperclass:helperclass=helperclass()
    var requestApi:RequestApi = RequestApi()


    //private var adapter: ArrayAdapter<String>?=null
    //var Items: MutableSet<String> = HashSet()
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.dummy1 ->{
            this.startActivity(Intent(this@MainActivity, MainActivity::class.java))
            true
        }
        else ->super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topstories()

        //val x= topstories.getString(0)

        //Log.d(helperclass.userNameForLogging,x)
        //val text:TextView= findViewById(R.id.textView)
        //text.text=x

        /*val litview:ListView = findViewById(R.id.dummy1)

        if ( Items.size > 0) {
            adapter = ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1,
                    Items.toTypedArray()
            )
            litview.adapter =adapter
        }*/

    }

   /* override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }*/




}