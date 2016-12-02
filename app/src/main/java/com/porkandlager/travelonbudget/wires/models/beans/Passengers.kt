package com.porkandlager.travelonbudget.wires.models.beans

import com.google.gson.annotations.SerializedName

/**
 * Created by tista on 11/12/16.
 */

class Passengers {

    @SerializedName("adults")
    var adults = 0

    @SerializedName("children")
    var children = 0

    @SerializedName("infants")
    var infants = 0

}
