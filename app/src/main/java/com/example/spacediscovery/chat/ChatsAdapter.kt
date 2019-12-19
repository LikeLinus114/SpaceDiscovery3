package com.example.spacediscovery.chat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
import com.example.spacediscovery.services.ChatsService
import com.example.spacediscovery.stations.ChatDetailsActivity

class ChatsAdapter: RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

    class ChatsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val stationIcon: ImageView = view.findViewById(R.id.station_icon)
        val stationName: TextView = view.findViewById(R.id.station_name)
        val time: TextView = view.findViewById(R.id.time)
        val message: TextView = view.findViewById(R.id.message)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        itemView.setOnClickListener {
            val intent = Intent(it.context, ChatDetailsActivity::class.java)
            it.context.startActivity(intent)
        }
        return ChatsViewHolder(itemView)
    }

    override fun getItemCount() = ChatsService.currentChats.size

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val chat = ChatsService.currentChats[position]
        holder.stationIcon.setImageBitmap(chat.station.imageBitMap)
        holder.stationName.text = chat.station.name
        holder.time.text = chat.lastMessage.time.toString()
        holder.message.text = chat.lastMessage.text
    }

}
