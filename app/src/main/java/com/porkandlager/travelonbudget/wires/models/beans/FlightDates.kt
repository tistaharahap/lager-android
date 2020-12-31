package com.porkandlager.travelonbudget.wires.models.beans

import com.google.gson.annotations.SerializedName
import com.porkandlager.travelonbudget.wires.Utils

/**
 * Created by tista on 11/12/16.
 */

class FlightDates {

    @SerializedName("outbound")
    var outbound: String? = null

    @SerializedName("inbound")
    var inbound: String? = null

    val outboundFormattedShortMonthYear: String
        get() = Utils.getDateFormattedShortMonth(outbound)

    val inboundFormattedShortMonthYear: String
        get() = Utils.getDateFormattedShortMonth(inbound)

}
