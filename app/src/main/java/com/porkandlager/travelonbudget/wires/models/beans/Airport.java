package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class Airport {

    @SerializedName("airport_name")
    private String name;

    @SerializedName("country_name")
    private String country;

    @SerializedName("city_id")
    private String cityId;

    @SerializedName("city_name")
    private String city;

    @SerializedName("iata_code")
    private String IataCode;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public String getIataCode() {
        return IataCode;
    }

}
