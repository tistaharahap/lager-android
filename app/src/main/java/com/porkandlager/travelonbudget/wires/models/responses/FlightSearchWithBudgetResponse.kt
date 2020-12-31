package com.porkandlager.travelonbudget.wires.models.responses

import com.google.gson.annotations.SerializedName
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch

/**
 * Created by tista on 11/12/16.
 */

class FlightSearchWithBudgetResponse {

    val status: Int = 0
    val message: String? = null

    @SerializedName("data")
    val flights: List<FlightSearch>? = null

}
