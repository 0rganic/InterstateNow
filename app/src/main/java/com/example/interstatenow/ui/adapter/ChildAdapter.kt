package com.example.interstatenow.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interstatenow.R
import com.example.interstatenow.RestAreaChild
import com.example.interstatenow.ui.DetailRestArea

class ChildAdapter(private val listRestAreaChild: List<RestAreaChild>): RecyclerView.Adapter<ChildAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvKm: TextView = view.findViewById(R.id.tv_km)
        val img: ImageView = view.findViewById(R.id.img_restarea)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false))


    override fun getItemCount() = listRestAreaChild.size

    override fun onBindViewHolder(holder: ChildAdapter.ViewHolder, position: Int) {
    val restAreaChild = listRestAreaChild[position]

    holder.tvKm.text = listRestAreaChild[position].rest_area_name
        listRestAreaChild[position].images?.let { holder.img.setImageResource(it) }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailRestArea::class.java)
            intent.putExtra("restAreaId", restAreaChild.id)
            intent.putExtra("toll id", restAreaChild.toll_id)
            context.startActivity(intent)
        }

    }
}

