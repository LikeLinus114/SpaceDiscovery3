package alexcaywalt.magistracy.spacediscovery.main_functionality.galaxy_map

import alexcaywalt.magistracy.spacediscovery.main_functionality.MapUtils.Companion.addTilesToInternalStorage
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.main_functionality.basic_models.SpaceMap
import alexcaywalt.magistracy.spacediscovery.di.Injectable
import alexcaywalt.magistracy.spacediscovery.main_functionality.galaxy_map.viewmodel.GalaxyMapViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.app_bar_side_pane.*
import kotlinx.android.synthetic.main.fragment_galaxy_map.*
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GalaxyMapFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModel: GalaxyMapViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_galaxy_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.toolbar!!.title = resources.getString(R.string.galaxy_map)
        update_button.setOnClickListener {
            viewModel.fetchGalaxyMap()
            observeViewModel(savedInstanceState)
        }
        update_button.performClick()
    }

    private fun observeViewModel(savedInstanceState: Bundle?) {
        viewModel.galaxyMapElements.removeObservers(this)
        viewModel.galaxyMapElements.observe(this, Observer { elements ->
            when {
                elements.loading -> {
                    update_request_status.text = resources.getString(R.string.in_processing)
                    update_request_status.setTextColor(resources.getColor(R.color.colorYellow, resources.newTheme()))
                    update_button.isEnabled = false
                    update_button.setTextColor(resources.getColor(R.color.colorYellowDark, resources.newTheme()))
                    loading_spinner.visibility = View.VISIBLE
                    shadow_view.visibility = View.VISIBLE
                }
                elements.error -> {
                    update_request_status.text = resources.getString(R.string.could_not_update)
                    update_request_status.setTextColor(resources.getColor(R.color.colorRed, resources.newTheme()))
                    update_button.isEnabled = true
                    update_button.setTextColor(resources.getColor(R.color.colorYellow, resources.newTheme()))
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    Toast.makeText(this.context, resources.getString(R.string.could_not_load_galaxy_map), Toast.LENGTH_SHORT).show()
                    SpaceMap(
                        map_view,
                        savedInstanceState != null,
                        "tiles"
                    )
                }
                else -> {
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    try {
                        addTilesToInternalStorage(context!!, elements.data, "galaxy_map")
                        SpaceMap(
                            map_view,
                            savedInstanceState != null,
                            context!!.getExternalFilesDir(null)!!.absolutePath + "/galaxy_map"
                        )
                        update_request_status.text = resources.getString(R.string.updated_successfully)
                        update_request_status.setTextColor(resources.getColor(R.color.colorGreen, resources.newTheme()))
                        update_button.isEnabled = true
                        update_button.setTextColor(resources.getColor(R.color.colorYellow, resources.newTheme()))
                        last_update.text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

}