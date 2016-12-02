package com.porkandlager.travelonbudget.wires.models.beans

import com.google.gson.annotations.SerializedName

/**
 * Created by tista on 11/12/16.
 */

class Location {

    @SerializedName("lat")
    val latitude: Float = 0.toFloat()

    @SerializedName("lon")
    val longitude: Float = 0.toFloat()

}
