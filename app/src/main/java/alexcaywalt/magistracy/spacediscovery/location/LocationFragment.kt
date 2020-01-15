package alexcaywalt.magistracy.spacediscovery.location

import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.SpaceMap
import alexcaywalt.magistracy.spacediscovery.di.Injectable
import alexcaywalt.magistracy.spacediscovery.location.viewmodel.SystemMapViewModel
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
import kotlinx.android.synthetic.main.fragment_location.*
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class LocationFragment: Fragment(), Injectable {

    companion object {
        private var successfulAttempts = 0
        private var failedAttempts = 0
    }

    @Inject
    lateinit var viewModel: SystemMapViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (successfulAttempts + failedAttempts > 0) {
            connection_rate.text = String.format("%d", (successfulAttempts / (successfulAttempts + failedAttempts) * 100))
        }

        viewModel.fetchSystemMap()
        observeViewModel(savedInstanceState)
    }

    private fun observeViewModel(savedInstanceState: Bundle?) {
        viewModel.systemMapElements.removeObservers(activity!!)
        viewModel.systemMapElements.observe(activity!!, Observer { elements ->
            when {
                elements.loading -> {
                    loading_spinner.visibility = View.VISIBLE
                    shadow_view.visibility = View.VISIBLE
                }
                elements.error -> {
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    Toast.makeText(this.context, "could not load the system map", Toast.LENGTH_SHORT).show()
                    SpaceMap(map_view, savedInstanceState != null, "tiles")
                    failedAttempts++
                    connection_rate.text = String.format("%d%%", (successfulAttempts / (successfulAttempts + failedAttempts) * 100))
                }
                else -> {
                    loading_spinner.visibility = View.GONE
                    shadow_view.visibility = View.GONE
                    try {
                        //create a directory for storing system map elements
                        val dir = File(context!!.getExternalFilesDir(null)!!.absolutePath + "/system_map")
                        if (dir.mkdir()) {
                            Log.i("System Map Fragment", "Directory created")
                        } else {
                            //if a directory has already been created
                            Log.i("System Map Fragment", "Directory is not created")
                        }
                        elements.data.forEach {
                            //decode a byte array from the string
                            val byteArray = Base64.getMimeDecoder().decode(it.encodedImage)
                            //decode a bitmap from the byte array
                            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                            //create an image file
                            val imageFile = File(context!!.getExternalFilesDir(null)!!.absolutePath + "/system_map", it.name)
                            val out = FileOutputStream(imageFile)
                            //save the image to its file
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                            out.flush()
                            out.close()
                        }
                        SpaceMap(map_view, savedInstanceState != null, context!!.getExternalFilesDir(null)!!.absolutePath + "/system_map")
                        successfulAttempts++
                        last_update.text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"))
                        connection_rate.text = String.format("%d%%", (successfulAttempts / (successfulAttempts + failedAttempts) * 100))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

}