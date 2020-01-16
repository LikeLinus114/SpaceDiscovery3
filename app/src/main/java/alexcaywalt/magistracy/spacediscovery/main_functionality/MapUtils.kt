package alexcaywalt.magistracy.spacediscovery.main_functionality

import alexcaywalt.magistracy.spacediscovery.main_functionality.galaxy_map.model.MapElement
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.util.*

class MapUtils {

    companion object {

        fun addTilesToInternalStorage(context: Context, tiles: List<MapElement>, folderName: String) {
            //create a directory for storing the map elements
            val dir = File(context.getExternalFilesDir(null)!!.absolutePath + "/" + folderName)
            if (dir.mkdir()) {
                Log.i("$folderName Fragment", "Directory created")
            } else {
                //if a directory has already been created
                Log.i("$folderName Fragment", "Directory is not created")
            }
            tiles.forEach {
                //decode a byte array from the string
                val byteArray = Base64.getMimeDecoder().decode(it.encodedImage)
                //decode a bitmap from the byte array
                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                //create an image file
                val imageFile = File(context.getExternalFilesDir(null)!!.absolutePath + "/" + folderName, it.name)
                val out = FileOutputStream(imageFile)
                //save the image to its file
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
                out.close()
            }
        }

    }

}
