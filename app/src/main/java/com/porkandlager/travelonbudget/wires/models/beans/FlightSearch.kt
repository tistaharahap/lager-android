package com.porkandlager.travelonbudget.wires.models.beans

import com.google.gson.annotations.SerializedName

/**
 * Created by tista on 11/12/16.
 */

class FlightSearch {

    @SerializedName("airports")
    var airports: Airports? = null

    @SerializedName("referral_link")
    var referralLink: String? = null

    @SerializedName("inbound")
    var inbound: Quote? = null

    @SerializedName("outbound")
    var outbound: Quote? = null

    @SerializedName("cheapest")
    var cheapestPrice: Int = 0

    @SerializedName("dates")
    var flightDates: FlightDates? = null

    @SerializedName("contents")
    var content: Content? = null

}
