package fi.sasu.hackernewapp.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.itemObject.Model
import fi.sasu.hackernewapp.service.MyApplication

class ItemFragment:Fragment(){

    public fun ItemFragment(){}
    private val itemsForTest2=ArrayList<Model>()
    //val itemsFragment:ItemAdapter=ItemAdapter()
    private val itemsForTest=ArrayList<Model>()
    private val helperclass = helperclass()
    private lateinit var recyclerView:RecyclerView
    private  lateinit var v:View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_item_list,container,false)
        recyclerView= v.findViewById(R.id.list)
        recyclerView.layoutManager=LinearLayoutManager(context)

        if(MyApplication.instance?.topstoriesListInstant!!.isNotEmpty()){
            val listForView=MyApplication.instance?.topstoriesListInstant
            recyclerView.adapter=RecyclerViewAdapter(listForView!!){
                helperclass.naytaToast("it work",context!!)
            }
        }else{
            recyclerView.adapter=RecyclerViewAdapter(itemsForTest){
                helperclass.naytaToast("it work",context!!)
            }
        }
        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                if(MyApplication.instance?.topstoriesListInstant!!.isEmpty()){
                    val testi = Model(1,"testi",0,"testi",0)
                    itemsForTest2.add(testi)
                    itemsForTest2.add(testi)
                    itemsForTest2.add(testi)
                    itemsForTest2.add(testi) }
    }
}