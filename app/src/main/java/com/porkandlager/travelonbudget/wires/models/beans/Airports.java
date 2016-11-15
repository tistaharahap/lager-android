package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class Airports {

    @SerializedName("origin")
    private Airport origin;

    @SerializedName("destination")
    private Airport destination;

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
}
