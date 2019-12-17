package com.example.spacediscovery.stations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R

class StationsAdapter: RecyclerView.Adapter<StationsAdapter.StationsViewHolder>() {

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

    override fun getItemCount(): Int {
        return StationService.preparedStations.size
    }

    override fun onBindViewHolder(holder: StationsViewHolder, position: Int) {
        val station: Station = StationService.preparedStations[position]
        holder.name.text = station.name
        holder.type.text = station.type!!.name
        holder.distance.text = station.distance.toString()
        holder.signalQuality.text = station.signalQuality.toString()
        holder.description.text = station.description
        holder.image.setImageResource(station.imageResourceId!!)
        holder.showMessagesHistory.setOnClickListener {
            Toast.makeText(holder.itemView.context, "To be implemented", Toast.LENGTH_SHORT).show()
        }
    }


}