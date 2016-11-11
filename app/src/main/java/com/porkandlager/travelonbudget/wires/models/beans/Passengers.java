package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class Passengers {

    @SerializedName("adults")
    private int adults;

    @SerializedName("children")
    private int children;

    @SerializedName("infants")
    private int infants;

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }
}
