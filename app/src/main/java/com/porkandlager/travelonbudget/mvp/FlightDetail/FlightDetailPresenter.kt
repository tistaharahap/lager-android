package com.porkandlager.travelonbudget.mvp.FlightDetail

import android.content.Intent

import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse

/**
 * Created by tista on 11/15/16.
 */

internal interface FlightDetailPresenter {

    fun parseIntent(intent: Intent)
    fun bookNowClicked()
    fun showPhotosFromImageSearch(response: FlightDetailImageSearchResponse)

}
