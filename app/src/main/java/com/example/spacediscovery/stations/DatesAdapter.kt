package com.example.spacediscovery.stations

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
import com.example.spacediscovery.services.DatesService

class DatesAdapter: RecyclerView.Adapter<DatesAdapter.DatesViewHolder>() {

    class DatesViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val date: TextView = view.findViewById(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatesViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_date, parent, false)
        itemView.setOnClickListener {
            val intent = Intent(it.context, ChatDetailsActivity::class.java)
            it.context.startActivity(intent)
        }
        return DatesViewHolder(itemView)
    }

    override fun getItemCount() = DatesService.dates.size

    override fun onBindViewHolder(holder: DatesViewHolder, position: Int) {
        val date = DatesService.dates[position]
        holder.date.text = date.toString()
    }
}
