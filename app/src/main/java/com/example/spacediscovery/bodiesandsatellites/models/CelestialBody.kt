package com.example.spacediscovery.bodiesandsatellites.models

import android.graphics.Bitmap

class CelestialBody(
    var name: String,
    var type: Int?,
    var distance: Long,
    var description: String?,
    var encodedImage: String?,
    var imageBitMap: Bitmap
)