package com.example.spacediscovery.stations

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
import com.example.spacediscovery.Shared
import com.example.spacediscovery.chat.ChatActivity
import com.example.spacediscovery.chat.ChatListActivity
import java.util.*
import kotlin.collections.ArrayList

class StationsAdapter(private var stations: ArrayList<Station>): RecyclerView.Adapter<StationsAdapter.StationsViewHolder>() {

    class StationsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.station_name)
        var type: TextView = view.findViewById(R.id.station_type)
        var distance: TextView = view.findViewById(R.id.station_distance)
        var signalQuality: TextView = view.findViewById(R.id.station_signal_quality)
        var description: TextView = view.findViewById(R.id.station_description)
        var image: ImageView = view.findViewById(R.id.station_image)
        var sendMessage: Button = view.findViewById(R.id.send_message)
        var showChatHistory: Button = view.findViewById(R.id.show_chat_history)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_station, parent, false)
        return StationsViewHolder(itemView)
    }

    fun updateStations(newStations: List<Station>) {
        stations.clear()
        stations.addAll(newStations)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    override fun onBindViewHolder(holder: StationsViewHolder, position: Int) {
        val station: Station = stations[position]
        holder.name.text = station.name
        holder.type.text = StationTypeEnum
            .values()
            .find { it.id == station.type }!!
            .name
            .toLowerCase(Locale.getDefault())
            .replace("_"," ")
        holder.distance.text = station.distance.toString()
        holder.signalQuality.text = station.signalQuality.toString()
        println(holder.itemView.resources.getString(R.string.description, station.description))
        val description = holder.itemView.resources.getString(R.string.description, station.description)
        holder.description.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)
        holder.image.setImageBitmap(station.imageBitMap)
        holder.sendMessage.setOnClickListener {
            Shared.currentStation = station
            val intent = Intent(holder.itemView.context, ChatActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
        holder.showChatHistory.setOnClickListener {
            Shared.currentStation = station
            val intent = Intent(holder.itemView.context, ChatListActivity::class.java)
            intent.putExtra("purpose", "history")
            holder.itemView.context.startActivity(intent)
        }
    }

}