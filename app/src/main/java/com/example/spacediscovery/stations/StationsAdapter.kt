package com.example.spacediscovery.stations

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
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
        var showMessagesHistory: Button = view.findViewById(R.id.show_messages_history)

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
        holder.description.text = station.description
        holder.image.setImageBitmap(station.imageBitMap)
        holder.showMessagesHistory.setOnClickListener {
            val intent = Intent(holder.itemView.context, ChatHistoryActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

}