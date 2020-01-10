package com.example.spacediscovery.location

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.spacediscovery.R
import com.moagrius.tileview.TileView
import com.moagrius.tileview.plugins.*
import java.util.*
import kotlin.collections.ArrayList

class LocationFragment: Fragment() {

    val NORTH = -75.17261900652977
    val WEST = 39.9639998777094
    val SOUTH = -75.12462846235614
    val EAST = 39.93699709962642

    private var mIsRestoring: Boolean = true

    private val sites = arrayListOf(
        doubleArrayOf(-75.1494000, 39.9487722),
        doubleArrayOf(-75.1468350, 39.9474180),
        doubleArrayOf(-75.1472000, 39.9482000),
        doubleArrayOf(-75.1437980, 39.9508290),
        doubleArrayOf(-75.1479650, 39.9523130)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tileView: TileView = activity!!.findViewById(R.id.tile_view)
        mIsRestoring = savedInstanceState != null
        val data1: String = "tiles/phi-1000000-%1\$d_%2\$d.jpg"
        println("ABCDCD")
        println(data1)
        TileView.Builder(tileView)
            .setSize(16384, 13056)
            .defineZoomLevel("tiles/phi-1000000-%1\$d_%2\$d.jpg")
            .defineZoomLevel(1, "tiles/phi-500000-%1\$d_%2\$d.jpg")
            .defineZoomLevel(2, "tiles/phi-250000-%1\$d_%2\$d.jpg")
            .installPlugin(MarkerPlugin(activity!!))
            //.installPlugin(ScalingMarkerPlugin(activity!!))
            .installPlugin(InfoWindowPlugin(getInfoView()))
            .installPlugin(CoordinatePlugin(WEST, NORTH, EAST, SOUTH))
            .installPlugin(HotSpotPlugin())
            .installPlugin(PathPlugin())
            .installPlugin(LowFidelityBackgroundPlugin(getBackgroundBitmap()))
            .addReadyListener(this::onReady)
            .build()
    }

    fun getBackgroundBitmap(): Bitmap? {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
        return BitmapFactory.decodeResource(resources, R.drawable.ic_planet, options)
    }

    private fun onReady(tileView: TileView) {
        val coordinatePlugin =
            tileView.getPlugin(CoordinatePlugin::class.java)
        val infoWindowPlugin =
            tileView.getPlugin(InfoWindowPlugin::class.java)
        val hotSpotPlugin =
            tileView.getPlugin(HotSpotPlugin::class.java)
        val markerPlugin = tileView.getPlugin(MarkerPlugin::class.java)
        val scalingMarkerPlugin: MarkerPlugin = tileView.getPlugin<MarkerPlugin>(
            MarkerPlugin::class.java
        )
        // drop some markers, with info window expansions
        val template = "Clicked marker at:\n%1\$f\n%2\$f"
        val markerClickListener =
            View.OnClickListener { view: View ->
                val coordinate = view.tag as DoubleArray
                val x = coordinatePlugin.longitudeToX(coordinate[1])
                val y = coordinatePlugin.latitudeToY(coordinate[0])
                tileView.smoothScrollTo(x - tileView.width / 2, y - tileView.height / 2)
                val label = String.format(
                    Locale.US,
                    template,
                    coordinate[0],
                    coordinate[1]
                )
                val infoView = infoWindowPlugin.getView<TextView>()
                infoView.text = label
                infoWindowPlugin.show(x, y, -0.5f, -1f)
            }
        var count = 0
        for (coordinate in sites) {
            val x = coordinatePlugin.longitudeToUnscaledX(coordinate[1])
            val y = coordinatePlugin.latitudeToUnscaledY(coordinate[0])
            val marker = ImageView(activity!!)
            marker.tag = coordinate
            marker.setImageResource(R.drawable.ic_location)
            marker.setOnClickListener(markerClickListener)
            if (count % 2 == 0) markerPlugin.addMarker(
                marker,
                x,
                y,
                -0.5f,
                -1f,
                0f,
                0f
            ) else scalingMarkerPlugin.addMarker(marker, x, y, -0.5f, -1f, 0.toFloat(), 0.toFloat())
            count++
        }
        markerPlugin.refreshPositions()
        // draw a path
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = -0xbd790c
        paint.strokeWidth = 0f
        paint.isAntiAlias = true
        val metrics = resources.displayMetrics
        paint.setShadowLayer(
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, metrics),
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, metrics),
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, metrics),
            0x66000000
        )
        paint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, metrics)
        paint.pathEffect = CornerPathEffect(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                5f,
                metrics
            )
        )
        val points: MutableList<Point> =
            ArrayList()
        for (coordinate in sites) {
            val point = Point()
            point.x = coordinatePlugin.longitudeToUnscaledX(coordinate[1])
            point.y = coordinatePlugin.latitudeToUnscaledY(coordinate[0])
            points.add(point)
        }
        val pathPlugin = tileView.getPlugin(PathPlugin::class.java)
        pathPlugin.drawPath(points, paint)
        // hotspot
        val hotSpot = hotSpotPlugin.addHotSpot(
            points
        ) { h: HotSpotPlugin.HotSpot ->
            Log.d(
                "TV",
                "hot spot touched: " + h.tag
            )
        }
        hotSpot.tag = "Any piece of data..."
        // frame it if it's a first launch, otherwise restore last position and scale (this is built in to TileView)
        if (!mIsRestoring) {
            val coordinate: DoubleArray = sites.get(0)
            val x = coordinatePlugin.longitudeToX(coordinate[1])
            val y = coordinatePlugin.latitudeToY(coordinate[0])
            tileView.scrollTo(x, y)
        }
    }

    private fun getInfoView(): View? {
        val elevation = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            resources.displayMetrics
        ).toInt()
        val padding = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            10f,
            resources.displayMetrics
        ).toInt()
        val infoView = TextView(activity!!)
        infoView.setPadding(padding, padding, padding, padding)
        infoView.setBackgroundColor(Color.WHITE)
        infoView.gravity = Gravity.CENTER
        infoView.setLineSpacing(0f, 1.3f)
        infoView.textSize = 11f
        ViewCompat.setElevation(infoView, elevation.toFloat())
        return infoView
    }

}