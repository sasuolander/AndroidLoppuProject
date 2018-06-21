package fi.sasu.hackernewapp.activity


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import fi.sasu.hackernewapp.fragment.ItemAdapter
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.fragment.ItemFragment
import fi.sasu.hackernewapp.method.RequestApi
import fi.sasu.hackernewapp.helperclass


class MainActivity : AppCompatActivity() {
    var adapter:ArrayAdapter<String>?=null
    var helperclass:helperclass=helperclass()
    var requestApi:RequestApi = RequestApi()


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


val supportFragment
        //add Frangment





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

Log.d(helperclass.userNameForLogging,x)

        val litview: ListView = findViewById(R.id.list_viewTest)

        if ( listJob !=null && listJob.isNotEmpty()) {
            adapter = ArrayAdapter(this,
                    android.R.layout.simple_expandable_list_item_1,
                    listJob.toTypedArray()
            )
            litview.adapter =adapter
        }
        */