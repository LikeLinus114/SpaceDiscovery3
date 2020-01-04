package com.example.spacediscovery.chat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
import com.example.spacediscovery.Shared
import com.example.spacediscovery.chat.models.Chat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class ChatsAdapter(private var chats: ArrayList<Chat>): RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

    class ChatsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val stationIcon: ImageView = view.findViewById(R.id.station_icon)
        val stationName: TextView = view.findViewById(R.id.station_name)
        val dateTime: TextView = view.findViewById(R.id.date_time)
        val sender: TextView = view.findViewById(R.id.sender)
        val message: TextView = view.findViewById(R.id.last_message)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ChatsViewHolder(itemView)
    }

    override fun getItemCount() = chats.size

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val chat = chats[position]
        holder.stationIcon.setImageBitmap(chat.station.imageBitMap)
        holder.stationName.text = chat.station.name
        holder.dateTime.text = LocalDateTime.parse(chat.getLastMessage().dateTime).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"))
        holder.sender.text = chat.getLastMessage().sender
        holder.message.text = chat.getLastMessage().text
        holder.itemView.setOnClickListener {
            Shared.currentStation = chats[position].station
            Shared.currentChat = chats[position]
            val intent = Intent(it.context, ChatActivity::class.java)
            intent.putExtra("isActive", false)
            it.context.startActivity(intent)
        }
    }

}
