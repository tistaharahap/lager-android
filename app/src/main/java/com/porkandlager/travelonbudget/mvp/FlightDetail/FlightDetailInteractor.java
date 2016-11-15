package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.content.Intent;

import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;

/**
 * Created by tista on 11/15/16.
 */

interface FlightDetailInteractor {

    FlightSearch buildFlightSearchBeanFromIntent(Intent intent);
    void requestImages(FlightSearch flightDetail);

}
