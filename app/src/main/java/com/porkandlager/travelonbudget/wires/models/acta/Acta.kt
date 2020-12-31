package com.porkandlager.travelonbudget.wires.models.acta

import com.google.gson.annotations.SerializedName

/**
 * Created by tista on 11/12/16.
 */

class Acta<T> {

    @SerializedName("actor")
    var actor: ActaObj? = null

    @SerializedName("action")
    var action: String? = null

    @SerializedName("object")
    var `object`: ActaObj? = null

    @SerializedName("meta")
    var meta: T? = null

}
