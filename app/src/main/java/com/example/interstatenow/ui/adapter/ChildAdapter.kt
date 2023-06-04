package com.example.interstatenow.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interstatenow.R
import com.example.interstatenow.RestAreaChild

class ChildAdapter(private val listRestAreaChild: List<RestAreaChild>, private val onItemClick: (id: String) -> Unit): RecyclerView.Adapter<ChildAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvKm: TextView = view.findViewById(R.id.tv_km)
        val img: ImageView = view.findViewById(R.id.img_dishub)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false))


    override fun getItemCount() = listRestAreaChild.size

    override fun onBindViewHolder(holder: ChildAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")

    }
}

