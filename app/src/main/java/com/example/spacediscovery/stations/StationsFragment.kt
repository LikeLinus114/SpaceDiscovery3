package com.example.spacediscovery.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import com.example.spacediscovery.di.Injectable
import com.example.spacediscovery.services.StationService
import com.example.spacediscovery.stations.viewmodel.StationViewModel
import kotlinx.android.synthetic.main.fragment_stations.*
import javax.inject.Inject

class StationsFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModel: StationViewModel

    private lateinit var stationsAdapter: StationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stationsAdapter = StationsAdapter(arrayListOf())
        stations_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        stations_list.adapter = stationsAdapter

        viewModel.fetchStations()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stations.observe(this, Observer { stations ->
            val preparedStations = StationService.prepareStationsData(stations)
            stationsAdapter.updateStations(preparedStations)
        })
        viewModel.loading.observe(this, Observer { loading ->
            loading?.let { /*enableSpinner(it)*/ }
        })
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                if (it) {
                    /* show an appropriate message */
                }
            }
        })
    }

}