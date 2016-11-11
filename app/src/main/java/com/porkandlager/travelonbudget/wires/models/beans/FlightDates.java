package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class FlightDates {

    @SerializedName("outbound")
    private String outbound;

    @SerializedName("inbound")
    private String inbound;

    public String getOutbound() {
        return outbound;
    }

    public String getInbound() {
        return inbound;
    }

}
