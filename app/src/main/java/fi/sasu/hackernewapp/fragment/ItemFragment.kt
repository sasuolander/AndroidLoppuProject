package fi.sasu.hackernewapp.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import fi.sasu.hackernewapp.R
import fi.sasu.hackernewapp.helperclass
import fi.sasu.hackernewapp.itemObject.Model


class ItemFragment:Fragment(){



    //val itemsFragment:ItemAdapter=ItemAdapter()
    val itemsForTest=ArrayList<Model>()
    val helperclass = helperclass()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v:View = LayoutInflater.from(context).inflate(R.layout.itemlayout,container,false)
        val recyclerView:RecyclerView= RecyclerView(context)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=RecyclerViewAdapter(itemsForTest){
            helperclass.naytaToast("it work",context)
        }
        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
val testi:Model= Model(1,"testi",0,"testi",0)
        itemsForTest.add(testi)
        itemsForTest.add(testi)
        itemsForTest.add(testi)
        itemsForTest.add(testi)
    }




}