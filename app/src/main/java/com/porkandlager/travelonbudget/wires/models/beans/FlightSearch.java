package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tista on 11/12/16.
 */

public class FlightSearch {

    @SerializedName("airports")
    private Airports airports;

    @SerializedName("referral_link")
    private String referralLink;

    @SerializedName("inbound")
    private Quote inbound;

    @SerializedName("outbound")
    private Quote outbound;

    @SerializedName("cheapest")
    private int cheapestPrice;

    @SerializedName("dates")
    private FlightDates flightDates;

    @SerializedName("contents")
    private Content content;

    public Airports getAirports() {
        return airports;
    }

    public String getReferralLink() {
        return referralLink;
    }

    public Quote getInbound() {
        return inbound;
    }

    public Quote getOutbound() {
        return outbound;
    }

    public int getCheapestPrice() {
        return cheapestPrice;
    }

    public Content getContent() {
        return content;
    }
}
