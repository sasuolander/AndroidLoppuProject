package fi.sasu.hackernewapp.Fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.sasu.hackernewapp.ItemObject.Model
import fi.sasu.hackernewapp.R

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){


    var context:Context?=null
    var itemList=ArrayList<Model>()


    fun RecyclerViewAdapter(mContext:Context,list:ArrayList<Model>){
        this.context=mContext
        this.itemList=list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view:View=LayoutInflater.from(context).inflate(R.layout.itemlayout,parent,false)
        val vHolder= MyViewHolder(view)
        return vHolder
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(model: Model, listener: (Model) -> Unit) = with(itemView) {
            itemTitle.text =
            itemImage.loadUrl(item.url)
            setOnClickListener { listener(item) }
        }
    }

}