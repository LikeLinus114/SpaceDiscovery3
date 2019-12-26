package com.example.spacediscovery.bodiesandsatellites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacediscovery.R
import com.example.spacediscovery.bodiesandsatellites.viewmodel.CelestialBodyViewModel
import com.example.spacediscovery.di.Injectable
import com.example.spacediscovery.services.CelestialBodyService
import kotlinx.android.synthetic.main.activity_chat_list.*
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
        enableSpinner(true)
    }

    private fun observeViewModel() {
        viewModel.celestialBodies.observe(this, Observer { bodies ->
            val preparedBodies = CelestialBodyService.prepareBodiesData(bodies)
            bodiesAdapter.updateBodies(preparedBodies)
            if (preparedBodies.isEmpty()) {
                no_bodies_label.visibility = View.VISIBLE
                no_bodies_image.visibility = View.VISIBLE
            } else {
                no_bodies_label.visibility = View.INVISIBLE
                no_bodies_image.visibility = View.INVISIBLE
            }
        })
        viewModel.loading.observe(this, Observer { loading ->
            loading?.let { enableSpinner(it) }
        })
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                if (it) {
                    no_bodies_label.visibility = View.VISIBLE
                    no_bodies_image.visibility = View.VISIBLE
                    Toast.makeText(this.context, "could not load the celestial bodies info", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun enableSpinner(isEnabled: Boolean) {
        if (isEnabled) {
            loading_spinner.visibility = View.VISIBLE
            shadow_view.visibility = View.VISIBLE
            no_bodies_label.visibility = View.INVISIBLE
            no_bodies_image.visibility = View.INVISIBLE
        } else {
            loading_spinner.visibility = View.GONE
            shadow_view.visibility = View.GONE
        }
    }

}