package com.porkandlager.travelonbudget.wires.models.requests;

import com.google.gson.annotations.SerializedName;
import com.porkandlager.travelonbudget.wires.models.beans.Passengers;

/**
 * Created by tista on 11/12/16.
 */

public class FlightSearchRequestMeta {

    @SerializedName("currency")
    private String currency;

    @SerializedName("number")
    private String budget;

    @SerializedName("passengers")
    private Passengers passengers;

    public String getCurrency() {
        return currency;
    }

}
