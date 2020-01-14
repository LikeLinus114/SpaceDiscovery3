package alexcaywalt.magistracy.spacediscovery.galaxymap

import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.SpaceMap
import alexcaywalt.magistracy.spacediscovery.di.Injectable
import alexcaywalt.magistracy.spacediscovery.galaxymap.viewmodel.GalaxyMapViewModel
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_galaxy_map.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class GalaxyMapFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModel: GalaxyMapViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_galaxy_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update_button.setOnClickListener {
            viewModel.fetchGalaxyMap()
            observeViewModel(savedInstanceState)
        }
        update_button.performClick()
        //SpaceMap(tile_view, savedInstanceState != null, "tiles")
    }

    private fun observeViewModel(savedInstanceState: Bundle?) {
        viewModel.galaxyMapElements.observe(this, Observer { elements ->
            try {
                //create a directory for storing galaxy map elements
                val dir = File(context!!.getExternalFilesDir(null)!!.absolutePath + "/galaxy_map")
                if (dir.mkdir()) {
                    Log.i("Galaxy Map Fragment", "Directory created")
                } else {
                    //if a directory has already been created
                    Log.i("Galaxy Map Fragment", "Directory is not created")
                }
                elements.forEach {
                    //decode a byte array from the string
                    val byteArray = Base64.getMimeDecoder().decode(it.encodedImage)
                    //decode a bitmap from the byte array
                    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                    //create an image file
                    val imageFile = File(context!!.getExternalFilesDir(null)!!.absolutePath + "/galaxy_map", it.name)
                    val out = FileOutputStream(imageFile)
                    //save the image to its file
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                    out.flush()
                    out.close()
                }
                SpaceMap(tile_view, savedInstanceState != null, context!!.getExternalFilesDir(null)!!.absolutePath + "/galaxy_map")
                update_request_status.text = "updated successfully"
                update_request_status.setTextColor(resources.getColor(R.color.colorGreen, resources.newTheme()))
                update_button.isEnabled = true
                update_button.setTextColor(resources.getColor(R.color.colorYellow, resources.newTheme()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                update_request_status.text = "in processing"
                update_request_status.setTextColor(resources.getColor(R.color.colorYellow, resources.newTheme()))
                update_button.isEnabled = false
                update_button.setTextColor(resources.getColor(R.color.colorYellowDark, resources.newTheme()))
                loading_spinner.visibility = View.VISIBLE
                shadow_view.visibility = View.VISIBLE
            } else {
                loading_spinner.visibility = View.INVISIBLE
                shadow_view.visibility = View.INVISIBLE
            }
        })
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                if (it) {
                    Toast.makeText(this.context, "could not load the galaxy map", Toast.LENGTH_SHORT).show()
                    update_request_status.text = "could not update"
                    update_request_status.setTextColor(resources.getColor(R.color.colorRed, resources.newTheme()))
                    update_button.isEnabled = true
                    update_button.setTextColor(resources.getColor(R.color.colorYellow, resources.newTheme()))
                    SpaceMap(tile_view, savedInstanceState != null, "tiles")
                }
            }
        })
    }

}