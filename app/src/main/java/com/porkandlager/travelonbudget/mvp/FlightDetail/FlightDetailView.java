package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.app.Activity;

import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution;

import java.util.List;

/**
 * Created by tista on 11/15/16.
 */

interface FlightDetailView {

    void makeFullscreen();
    Activity getActivity();
    void populateViewsFromFlightDetail(FlightSearch flightDetail);
    void populateMorePhotos(List<PhotoWithAttribution> photos);

}
