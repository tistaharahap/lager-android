package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.content.Intent;

import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse;

/**
 * Created by tista on 11/15/16.
 */

interface FlightDetailPresenter {

    void parseIntent(Intent intent);
    void bookNowClicked();
    void showPhotosFromImageSearch(FlightDetailImageSearchResponse response);

}
