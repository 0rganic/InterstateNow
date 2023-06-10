package com.example.interstatenow.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.interstatenow.R
import com.example.interstatenow.response.RestAreaChild
import com.example.interstatenow.ui.activity.DetailRestArea

class ChildAdapter(private var listRestAreaChild: List<RestAreaChild>): RecyclerView.Adapter<ChildAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvKm: TextView = view.findViewById(R.id.tv_km)
        val img: ImageView = view.findViewById(R.id.img_restarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false))


    override fun getItemCount() = listRestAreaChild.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val restAreaChild = listRestAreaChild[position]

        holder.tvKm.text = restAreaChild.name

        Glide.with(holder.itemView)
            .load(restAreaChild.image)
            .into(holder.img)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailRestArea::class.java)
            intent.putExtra("restAreaImg", restAreaChild.image)
            context.startActivity(intent)
        }

    }
}

