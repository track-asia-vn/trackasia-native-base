package com.trackasia.android.testapp.activity.imagegenerator

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.print.PrintHelper
import com.trackasia.android.maps.MapView
import com.trackasia.android.maps.TrackAsiaMap
import com.trackasia.android.maps.OnMapReadyCallback
import com.trackasia.android.maps.Style
import com.trackasia.android.testapp.R

/**
 * Test activity showcasing using the Snaphot API to print a Map.
 */
class PrintActivity : AppCompatActivity(), TrackAsiaMap.SnapshotReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var trackasiaMap: TrackAsiaMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(OnMapReadyCallback { trackasiaMap: TrackAsiaMap -> initMap(trackasiaMap) })
        val fab = findViewById<View>(R.id.fab)
        fab?.setOnClickListener { view: View? ->
            if (trackasiaMap != null && trackasiaMap.style != null) {
                trackasiaMap.snapshot(this@PrintActivity)
            }
        }
    }

    private fun initMap(trackasiaMap: TrackAsiaMap) {
        this.trackasiaMap = trackasiaMap
        trackasiaMap.setStyle(Style.getPredefinedStyle("Streets"))
    }

    override fun onSnapshotReady(snapshot: Bitmap) {
        val photoPrinter = PrintHelper(this)
        photoPrinter.scaleMode = PrintHelper.SCALE_MODE_FIT
        photoPrinter.printBitmap("map.jpg - mapbox print job", snapshot)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
