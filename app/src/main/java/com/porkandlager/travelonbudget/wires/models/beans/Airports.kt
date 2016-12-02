package com.porkandlager.travelonbudget.wires.models.beans

import com.google.gson.annotations.SerializedName

/**
 * Created by tista on 11/12/16.
 */

class Airports {

    @SerializedName("origin")
    var origin: Airport? = null

    @SerializedName("destination")
    var destination: Airport? = null

}
