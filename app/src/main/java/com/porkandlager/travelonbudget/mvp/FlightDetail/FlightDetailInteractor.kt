package com.porkandlager.travelonbudget.mvp.FlightDetail

import android.content.Intent

import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch

/**
 * Created by tista on 11/15/16.
 */

internal interface FlightDetailInteractor {

    fun buildFlightSearchBeanFromIntent(intent: Intent): FlightSearch
    fun requestImages(flightDetail: FlightSearch)
    fun logBookNowClick(destination: String, price: String)

}
