package com.porkandlager.travelonbudget.wires.models.requests;

import com.google.gson.annotations.SerializedName;
import com.porkandlager.travelonbudget.wires.models.beans.FlightDates;
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

    @SerializedName("dates")
    private FlightDates dates;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Passengers getPassengers() {
        return passengers;
    }

    public void setPassengers(Passengers passengers) {
        this.passengers = passengers;
    }

    public FlightDates getDates() {
        return dates;
    }

    public void setDates(FlightDates dates) {
        this.dates = dates;
    }
}
