package fi.sasu.hackernewapp.fragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.sasu.hackernewapp.itemObject.Model
import fi.sasu.hackernewapp.R
import kotlinx.android.synthetic.main.itemlayout.view.*

class RecyclerViewAdapter(val itemList:ArrayList<Model>, val listener: (Model) -> Unit): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.itemlayout))

    override fun getItemCount(): Int=itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =holder.bind(itemList[position],listener)


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(model: Model, listener: (Model) -> Unit) = with(itemView) {
    IdTitle.text=model.titleObject
            IdUrl.text=model.urlObject
            IdScore.text=model.scoreObject.toString()
            IdBy.text=model.byObject
            IdTime.text=model.timeObject.toString()
            setOnClickListener { listener(model) }
        }
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}