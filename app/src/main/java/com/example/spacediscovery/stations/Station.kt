package com.example.spacediscovery.stations

import android.graphics.Bitmap

class Station(
    var name: String,
    var type: StationTypeEnum?,
    var distance: Long,
    var signalQuality: String?,
    var description: String?,
    var encodedImage: String?,
    var imageBitMap: Bitmap
)