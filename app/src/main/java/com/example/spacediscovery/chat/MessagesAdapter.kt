package com.example.spacediscovery.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
import com.example.spacediscovery.services.MessagesService

class MessagesAdapter: RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>() {

    class MessagesViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val message: TextView = view.findViewById(R.id.message)
        val sender: TextView = view.findViewById(R.id.sender)
        val time: TextView = view.findViewById(R.id.time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessagesViewHolder(
            itemView
        )
    }

    override fun getItemCount() = MessagesService.messages.size

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        val message = MessagesService.messages[position]
        holder.message.text = message.text
        holder.sender.text = message.sender
        holder.time.text = message.time.toString()
    }

}