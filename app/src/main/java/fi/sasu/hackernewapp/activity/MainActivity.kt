package fi.sasu.hackernewapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.fragment.RecyclerViewAdapter
import fi.sasu.hackernewapp.method.RequestApi
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.itemObject.Model
import fi.sasu.hackernewapp.service.MyApplication

class MainActivity : AppCompatActivity() {
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
        //val viewPager: ViewPager = findViewById(R.id.mainview)
        //val itemAdapter:ItemAdapter = ItemAdapter(supportFragmentManager)

        if(MyApplication.instance?.topstoriesListInstant!=null){
            val listForView=MyApplication.instance?.topstoriesListInstant

            viewManager = LinearLayoutManager(this)
            viewAdapter =RecyclerViewAdapter(listForView!!){
                helperclass.naytaToast("it work",applicationContext)
            }
        }else{
            val testi = Model(1,"testi",0,"testi",0)
            itemsForTest2.add(testi)
            itemsForTest2.add(testi)
            itemsForTest2.add(testi)
            itemsForTest2.add(testi)

            viewManager = LinearLayoutManager(this)
            viewAdapter =RecyclerViewAdapter(itemsForTest2 ){
                helperclass.naytaToast("it work",applicationContext)
            }
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