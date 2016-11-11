package com.porkandlager.travelonbudget.wires.models.responses;

import com.google.gson.annotations.SerializedName;
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;

import java.util.List;

/**
 * Created by tista on 11/12/16.
 */

public class FlightSearchWithBudgetResponse {

    private int status;
    private String message;

    @SerializedName("data")
    private List<FlightSearch> flights;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<FlightSearch> getFlights() {
        return flights;
    }

}
