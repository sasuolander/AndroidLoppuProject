package fi.sasu.hackernewapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.adapter.ItemAdapter
import fi.sasu.hackernewapp.fragment.*
import fi.sasu.hackernewapp.method.RequestApi
import fi.sasu.hackernewapp.helperclass

class MainActivity : AppCompatActivity() {
    private var helperclass:helperclass=helperclass()
    private var requestApi:RequestApi = RequestApi()
    private lateinit var adapter: ItemAdapter
    private lateinit var viewPager:ViewPager
    private lateinit var tab:TabLayout
    val askstories:AskstoriesFragment= AskstoriesFragment()
    val jobstories:JobstoriesFragment= JobstoriesFragment()
    val newstories:NewstoriesFragment= NewstoriesFragment()
    val showstories:ShowstoriesFragment= ShowstoriesFragment()
    val topstories: TopstoriesFragment =TopstoriesFragment()
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.reload ->{
            this.startActivity(Intent(this@MainActivity,
                    this@MainActivity::class.java))
            true }
        R.id.loadlist->{
            requestApi.topstories()
            requestApi.askstories()
            requestApi.jobstories()
            requestApi.newstories()
            requestApi.showstories()
            helperclass.naytaToast("pressed",applicationContext)
            true }
        R.id.loaditems->{
            requestApi.loadItems("topstories")
            requestApi.loadItems("newstories")
            requestApi.loadItems("showstories")
            requestApi.loadItems("askstories")
            requestApi.loadItems("jobstories")
            helperclass.naytaToast("pressed",applicationContext)
            true }
        else ->super.onOptionsItemSelected(item) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.ViewPagerID)
        tab =findViewById(R.id.tab)
        adapter = ItemAdapter(supportFragmentManager)

        adapter.addFragment(askstories,"Ask Stories")
        adapter.addFragment(jobstories,"Job Stories")
        adapter.addFragment(newstories,"New Stories")
        adapter.addFragment(showstories,"Show Stories")
        adapter.addFragment(topstories,"Top Stories")
        viewPager.adapter=adapter
        tab.setupWithViewPager(viewPager)
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