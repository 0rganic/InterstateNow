package com.example.interstatenow.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interstatenow.R
import com.example.interstatenow.response.RestAreaParent
import com.example.interstatenow.ui.SpaceItemDecoration

class ParentAdapter (private var listRestAreaParent: List<RestAreaParent>): RecyclerView.Adapter<ParentAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvToll: TextView = view.findViewById(R.id.tv_toll)
        val childRecyclerView: RecyclerView = view.findViewById(R.id.rv_child_item)
    }

    fun setFilteredList(mList: List<RestAreaParent>){
        this.listRestAreaParent = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restAreaParent = listRestAreaParent[position]
        holder.tvToll.text = restAreaParent.name

        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.childRecyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ChildAdapter(restAreaParent.list_restArea)
        holder.childRecyclerView.adapter = adapter

        val spaceWidthPx = holder.itemView.context.resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
        holder.childRecyclerView.addItemDecoration(SpaceItemDecoration(spaceWidthPx))

    }

    override fun getItemCount() = listRestAreaParent.size
}