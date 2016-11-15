package com.porkandlager.travelonbudget.wires.models.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/15/16.
 */

public class FlightDetailImageRequestMeta {

    @SerializedName("airport_iata_code")
    private String airportIataCode;

    public String getAirportIataCode() {
        return airportIataCode;
    }

    public void setAirportIataCode(String airportIataCode) {
        this.airportIataCode = airportIataCode;
    }
}
