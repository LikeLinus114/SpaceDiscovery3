package alexcaywalt.magistracy.spacediscovery.bodies_and_satellites.models

import android.graphics.Bitmap

class CelestialBody(
    var name: String,
    var type: Int?,
    var distance: Long,
    var description: String?,
    var encodedImage: String?,
    var imageBitMap: Bitmap
)