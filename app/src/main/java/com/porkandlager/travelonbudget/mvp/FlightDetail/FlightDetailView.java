package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.app.Activity;

import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;

/**
 * Created by tista on 11/15/16.
 */

public interface FlightDetailView {

    void makeFullscreen();
    Activity getActivity();
    void populateViewsFromFlightDetail(FlightSearch flightDetail);

}
