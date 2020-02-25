package com.example.exercise5.classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise5.R
import kotlinx.android.synthetic.main.listlayout.view.*

/**
 * Here we specify how the elements inside the RecyclerView will look like
 */
class MyRecyclerViewAdapter(private val context: Context, private val mydata: List<String>): RecyclerView.Adapter<MyViewHolder>() {
    @Synchronized
    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.listlayout, vg, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mydata.size
    }

    override fun onBindViewHolder(vh: MyViewHolder, position: Int) {
        vh.msgView.text = mydata[position]
    }
}

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val msgView = itemView.msgview!!
}