package alexcaywalt.magistracy.spacediscovery.galaxymap

import alexcaywalt.magistracy.spacediscovery.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class GalaxyMapFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_galaxy_map, container, false)
    }
}