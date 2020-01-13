package alexcaywalt.magistracy.spacediscovery.stations.models

import android.graphics.Bitmap

class Station(
    var name: String,
    var type: Int?,
    var distance: Long,
    var signalQuality: String?,
    var description: String?,
    var encodedImage: String?,
    var imageBitMap: Bitmap?
) {

    fun copyWithNoImage(): Station {
        return Station(
            name,
            type,
            distance,
            signalQuality,
            description,
            null,
            null
        )
    }

    override fun toString(): String {
        return "Station(name='$name', type=$type, distance=$distance, signalQuality=$signalQuality, description=$description, encodedImage=$encodedImage, imageBitMap=$imageBitMap)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Station

        if (name != other.name) return false
        if (type != other.type) return false
        if (distance != other.distance) return false
        if (signalQuality != other.signalQuality) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (type ?: 0)
        result = 31 * result + distance.hashCode()
        result = 31 * result + (signalQuality?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }

}