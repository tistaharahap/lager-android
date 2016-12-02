package com.porkandlager.travelonbudget.wires.models.beans

import com.google.gson.annotations.SerializedName

/**
 * Created by tista on 11/12/16.
 */

class Content {

    @SerializedName("wikipedia_url")
    var wikipediaUrl: String? = null

    @SerializedName("location")
    var location: Location? = null

    @SerializedName("picture")
    var pictureUrl: String? = null

    @SerializedName("description")
    var description: String? = null
}
