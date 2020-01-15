package alexcaywalt.magistracy.spacediscovery.stations

import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.di.Injectable
import alexcaywalt.magistracy.spacediscovery.services.StationService
import alexcaywalt.magistracy.spacediscovery.stations.viewmodel.StationViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.app_bar_side_pane.*
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
        viewModel.stations.removeObservers(activity!!)
        viewModel.stations.observe(activity!!, Observer { stations ->
            when {
                stations.loading -> {
                    loading_spinner.visibility = View.VISIBLE
                    shadow_view.visibility = View.VISIBLE
                    no_stations_label.visibility = View.GONE
                    no_stations_image.visibility = View.GONE
                }
                stations.error -> {
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    no_stations_label.visibility = View.VISIBLE
                    no_stations_image.visibility = View.VISIBLE
                    Toast.makeText(this.context, "could not load the stations info", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val preparedStations = StationService.prepareStationsData(stations.data)
                    stationsAdapter.updateStations(preparedStations)
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    if (preparedStations.isEmpty()) {
                        no_stations_label.visibility = View.VISIBLE
                        no_stations_image.visibility = View.VISIBLE
                    } else {
                        no_stations_label.visibility = View.GONE
                        no_stations_image.visibility = View.GONE
                    }
                }
            }
        })
    }

}