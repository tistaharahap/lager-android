package com.porkandlager.travelonbudget.wires.models.beans;

import com.google.gson.annotations.SerializedName;
import com.porkandlager.travelonbudget.wires.Utils;

/**
 * Created by tista on 11/12/16.
 */

public class FlightDates {

    @SerializedName("outbound")
    private String outbound;

    @SerializedName("inbound")
    private String inbound;

    public String getOutboundFormattedShortMonthYear() {
        return Utils.getDateFormattedShortMonth(outbound);
    }

    public String getInboundFormattedShortMonthYear() {
        return Utils.getDateFormattedShortMonth(inbound);
    }

    public String getOutbound() {
        return outbound;
    }

    public String getInbound() {
        return inbound;
    }

    public void setOutbound(String outbound) {
        this.outbound = outbound;
    }

    public void setInbound(String inbound) {
        this.inbound = inbound;
    }
}
