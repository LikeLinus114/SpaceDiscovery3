package com.example.spacediscovery.bodiesandsatellites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spacediscovery.R
import com.example.spacediscovery.services.CelestialBodyService
import kotlinx.android.synthetic.main.item_celestial_body.view.*

class CelestialBodiesAdapter:
    RecyclerView.Adapter<CelestialBodiesAdapter.CelestialBodyViewHolder>() {

    override fun getItemCount(): Int {
        return CelestialBodyService.preparedBodies.size
    }

    class CelestialBodyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var name: TextView = view.body_name
        var type: TextView = view.body_type
        var distance: TextView = view.body_distance
        var description: TextView = view.body_description
        var image: ImageView = view.body_image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelestialBodyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_celestial_body, parent, false)
        return CelestialBodyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CelestialBodyViewHolder, position: Int) {
        val body: CelestialBody = CelestialBodyService.preparedBodies[position]
        holder.name.text = body.name
        holder.type.text = body.type!!.name
        holder.distance.text = body.distance.toString()
        holder.description.text = body.description.toString()
        holder.image.setImageResource(body.imageResourceId!!)
    }

}