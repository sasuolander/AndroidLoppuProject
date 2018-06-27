package fi.sasu.hackernewapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.fragment.RecyclerViewAdapter
import fi.sasu.hackernewapp.method.RequestApi
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.itemObject.Model


class MainActivity : AppCompatActivity() {
    private var adapter:ArrayAdapter<String>?=null
    private var helperclass:helperclass=helperclass()
    private var requestApi:RequestApi = RequestApi()
    private lateinit var supportFragment:String
    private lateinit var recyclerView:RecyclerView
    private lateinit var viewManager:LinearLayoutManager
    private lateinit var viewAdapter: RecyclerViewAdapter
    private val itemsForTest2=ArrayList<Model>()


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.fragmentpage ->{
            this.startActivity(Intent(this@MainActivity, this@MainActivity::class.java))
            true
        }
        else ->super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val viewPager: ViewPager = findViewById(R.id.mainview)
        //val itemAdapter:ItemAdapter = ItemAdapter(supportFragmentManager)

        val testObject2 =requestApi.itemobject(10)

        if (testObject2===null){
            helperclass.naytaToast("null",applicationContext)
        }else{
            helperclass.naytaToast("toimii",applicationContext)
        }

        val testi = Model(1,"testi",0,"testi",0)
        itemsForTest2.add(testi)
        itemsForTest2.add(testi)
        itemsForTest2.add(testi)
        itemsForTest2.add(testi)

        viewManager = LinearLayoutManager(this)
        viewAdapter =RecyclerViewAdapter(itemsForTest2 ){
            helperclass.naytaToast("it work",applicationContext)
        }

         recyclerView = findViewById<RecyclerView>(R.id.list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

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

/*
    requestApi.jobstories()
    val itemX: Item? =requestApi.itemobject(20)
    helperclass.naytaToast(itemX.toString(),applicationContext)
    val listJob:Set<String>? =MyApplication.instance?.jobstoriesInstant
    val x= topstories.getString(0)
        */