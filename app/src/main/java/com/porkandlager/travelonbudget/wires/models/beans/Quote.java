package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class Quote {

    @SerializedName("quote_id")
    private String id;

    @SerializedName("airline")
    private String airline;

    public String getId() {
        return id;
    }

    public String getAirline() {
        return airline;
    }

}
