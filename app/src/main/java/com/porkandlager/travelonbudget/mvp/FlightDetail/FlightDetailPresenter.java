package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.content.Intent;

/**
 * Created by tista on 11/15/16.
 */

interface FlightDetailPresenter {

    void parseIntent(Intent intent);
    void bookNowClicked();

}
