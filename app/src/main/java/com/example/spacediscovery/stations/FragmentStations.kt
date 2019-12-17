package com.example.spacediscovery.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import kotlinx.android.synthetic.main.fragment_stations.*

class FragmentStations: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stations_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        stations_list.adapter = StationsAdapter()
    }

}