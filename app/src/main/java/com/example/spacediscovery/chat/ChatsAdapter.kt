package com.example.spacediscovery.chat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
import com.example.spacediscovery.stations.ChatDetailsActivity

class ChatsAdapter(private var chats: ArrayList<Chat>): RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

    class ChatsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val stationIcon: ImageView = view.findViewById(R.id.station_icon)
        val stationName: TextView = view.findViewById(R.id.station_name)
        val dateTime: TextView = view.findViewById(R.id.date_time)
        val message: TextView = view.findViewById(R.id.last_message)

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

    fun updateChats(newChats: List<Chat>) {
        chats.clear()
        chats.addAll(newChats)
        notifyDataSetChanged()
    }

    override fun getItemCount() = chats.size

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val chat = chats[position]
        holder.stationIcon.setImageBitmap(chat.station.imageBitMap)
        holder.stationName.text = chat.station.name
        System.out.println(chat.messages.size)
        System.out.println("AAAAAAAAAAA")
        System.out.println(chat.messages[0].sender)
        System.out.println(chat.messages[0].text)
        System.out.println(chat.messages[0].dateTime)
        System.out.println(chat.messages[chat.messages.size - 1])
        holder.dateTime.text = chat.messages[chat.messages.size - 1].dateTime.toString()
        holder.message.text = chat.messages[chat.messages.size - 1].text
    }

}
