package alexcaywalt.magistracy.spacediscovery.main_functionality.bodies_and_satellites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.main_functionality.bodies_and_satellites.viewmodel.CelestialBodyViewModel
import alexcaywalt.magistracy.spacediscovery.di.Injectable
import alexcaywalt.magistracy.spacediscovery.main_functionality.services.CelestialBodyService
import kotlinx.android.synthetic.main.app_bar_side_pane.*
import kotlinx.android.synthetic.main.fragment_bodies_satellites.*
import javax.inject.Inject

class BodiesSatellitesFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModel: CelestialBodyViewModel

    private lateinit var bodiesAdapter: CelestialBodiesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bodies_satellites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.toolbar!!.title = resources.getString(R.string.celestial_bodies_and_satellites)
        bodiesAdapter = CelestialBodiesAdapter(arrayListOf())
        bodies_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        bodies_list.adapter = bodiesAdapter

        viewModel.fetchBodiesAndSatellites()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.celestialBodies.removeObservers(activity!!)
        viewModel.celestialBodies.observe(this, Observer { bodies ->
            when {
                bodies.loading -> {
                    loading_spinner.visibility = View.VISIBLE
                    shadow_view.visibility = View.VISIBLE
                    no_bodies_label.visibility = View.GONE
                    no_bodies_image.visibility = View.GONE
                }
                bodies.error -> {
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    no_bodies_label.visibility = View.VISIBLE
                    no_bodies_image.visibility = View.VISIBLE
                    Toast.makeText(this.context, "could not load the celestial bodies info", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val preparedBodies = CelestialBodyService.prepareBodiesData(bodies.data)
                    bodiesAdapter.updateBodies(preparedBodies)
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    if (preparedBodies.isEmpty()) {
                        no_bodies_label.visibility = View.VISIBLE
                        no_bodies_image.visibility = View.VISIBLE
                    } else {
                        no_bodies_label.visibility = View.GONE
                        no_bodies_image.visibility = View.GONE
                    }
                }
            }
        })
    }

}