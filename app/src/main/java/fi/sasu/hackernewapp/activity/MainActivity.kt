package fi.sasu.hackernewapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.fragment.ItemAdapter
import fi.sasu.hackernewapp.fragment.ItemFragment
import fi.sasu.hackernewapp.fragment.RecyclerViewAdapter
import fi.sasu.hackernewapp.method.RequestApi
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.itemObject.Model
import fi.sasu.hackernewapp.service.MyApplication

class MainActivity : AppCompatActivity() {
    private var helperclass:helperclass=helperclass()
    private var requestApi:RequestApi = RequestApi()
    private lateinit var supportFragment:String
    private lateinit var adapter:ItemAdapter
    private lateinit var viewPager:ViewPager
    private lateinit var tab:TabLayout
    val itemFragment: ItemFragment =ItemFragment()



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.reload ->{
            this.startActivity(Intent(this@MainActivity, this@MainActivity::class.java))
            true }
        R.id.loadlist->{
            requestApi.topstories()
            helperclass.naytaToast("pressed",applicationContext)
            true }
        R.id.loaditems->{
            requestApi.loadItems("topstories")
            helperclass.naytaToast("pressed",applicationContext)
            true }
        else ->super.onOptionsItemSelected(item) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.ViewPagerID)
        tab =findViewById(R.id.tab)
        adapter = ItemAdapter(supportFragmentManager)

        adapter.addFragment(itemFragment,"item")
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