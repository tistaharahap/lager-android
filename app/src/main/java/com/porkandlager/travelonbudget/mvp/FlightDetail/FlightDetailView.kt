package com.porkandlager.travelonbudget.mvp.FlightDetail

import android.app.Activity

import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution

/**
 * Created by tista on 11/15/16.
 */

internal interface FlightDetailView {

    fun makeFullscreen()
    val activity: Activity
    fun populateViewsFromFlightDetail(flightDetail: FlightSearch)
    fun populateMorePhotos(photos: List<PhotoWithAttribution>)

}
