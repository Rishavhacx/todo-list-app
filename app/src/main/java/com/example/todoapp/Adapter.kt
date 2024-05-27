package com.example.todoapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.title)!!
        var priority = itemView.findViewById<TextView>(R.id.priority)!!
        var layout = itemView.findViewById<LinearLayout>(R.id.mylayout)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = data[position].title
        holder.priority.text = data[position].priority

        when (data[position].priority.lowercase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "high " -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#fd8c00"))
            "medium " -> holder.layout.setBackgroundColor(Color.parseColor("#fd8c00"))
            "low " -> holder.layout.setBackgroundColor(Color.parseColor("#00BCD4"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00BCD4"))

        }


        // Corrected syntax for setting OnClickListener
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}