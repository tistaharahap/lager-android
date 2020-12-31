package com.porkandlager.travelonbudget.wires.models.beans

import com.google.gson.annotations.SerializedName

/**
 * Created by tista on 11/12/16.
 */

class Airport {

    @SerializedName("airport_name")
    var name: String? = null

    @SerializedName("country_name")
    var country: String? = null

    @SerializedName("city_id")
    var cityId: String? = null

    @SerializedName("city_name")
    var city: String? = null

    @SerializedName("iata_code")
    var iataCode: String? = null

}
