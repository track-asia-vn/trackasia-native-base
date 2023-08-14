package com.trackasia.android.testapp.activity

import androidx.annotation.UiThread
import com.trackasia.android.maps.TrackAsiaMap
import com.trackasia.android.maps.Style
import com.trackasia.android.testapp.activity.espresso.EspressoTestActivity

/**
 * Base class for all tests using EspressoTestActivity as wrapper.
 *
 *
 * Loads "assets/streets.json" as style.
 *
 */
open class EspressoTest : BaseTest() {
    override fun getActivityClass(): Class<*> {
        return EspressoTestActivity::class.java
    }

    @UiThread
    override fun initMap(trackasiaMap: TrackAsiaMap) {
        trackasiaMap.setStyle(Style.Builder().fromUri("asset://streets.json"))
        super.initMap(trackasiaMap)
    }
}
