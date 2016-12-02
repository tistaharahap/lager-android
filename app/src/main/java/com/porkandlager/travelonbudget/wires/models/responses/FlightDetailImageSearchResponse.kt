package com.porkandlager.travelonbudget.wires.models.responses

import com.google.gson.annotations.SerializedName
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution

/**
 * Created by tista on 11/15/16.
 */

class FlightDetailImageSearchResponse {

    var status: Int = 0
    var message: String? = null

    @SerializedName("data")
    var photos: List<PhotoWithAttribution>? = null

}
