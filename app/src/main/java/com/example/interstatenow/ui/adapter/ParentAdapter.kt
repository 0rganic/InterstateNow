package com.example.interstatenow.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interstatenow.R
import com.example.interstatenow.ui.RestAreaParent

class ParentAdapter (private val listRestAreaParent: List<RestAreaParent>, private val itemClick: (id: String?) -> Unit): RecyclerView.Adapter<ParentAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvToll: TextView = view.findViewById(R.id.tv_toll)
        val childRecyclerView: RecyclerView = view.findViewById(R.id.rv_child_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restAreaParent = listRestAreaParent[position]
        holder.tvToll.text = restAreaParent.toll_name

        holder.childRecyclerView.setHasFixedSize(true)
        val adapter = ChildAdapter(restAreaParent.mList)
        {
            itemClick(restAreaParent.id)
        }



    }


    override fun getItemCount() = listRestAreaParent.size
}