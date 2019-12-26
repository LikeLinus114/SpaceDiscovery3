package com.example.spacediscovery.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import com.example.spacediscovery.di.Injectable
import com.example.spacediscovery.services.StationService
import com.example.spacediscovery.stations.viewmodel.StationViewModel
import kotlinx.android.synthetic.main.activity_chat.*
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
        activity!!.toolbar!!.title = resources.getString(R.string.stations)
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
            if (preparedStations.isEmpty()) {
                no_stations_label.visibility = View.VISIBLE
            } else {
                no_stations_label.visibility = View.INVISIBLE
            }
        })
        viewModel.loading.observe(this, Observer { loading ->
            loading?.let { enableSpinner(it) }
        })
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                if (it) {
                    no_stations_label.visibility = View.VISIBLE
                    Toast.makeText(this.context, "could not load the stations info", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun enableSpinner(isEnabled: Boolean) {
        if (isEnabled) {
            loading_spinner.visibility = View.VISIBLE
            shadow_view.visibility = View.VISIBLE
            no_stations_label.visibility = View.INVISIBLE
        } else {
            loading_spinner.visibility = View.GONE
            shadow_view.visibility = View.GONE
        }
    }

}