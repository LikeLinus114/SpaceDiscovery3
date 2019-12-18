package com.example.spacediscovery.bodiesandsatellites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import kotlinx.android.synthetic.main.fragment_bodies_satellites.*

class FragmentBodiesSatellites: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bodies_satellites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bodies_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        bodies_list.adapter = CelestialBodiesAdapter()
    }

}