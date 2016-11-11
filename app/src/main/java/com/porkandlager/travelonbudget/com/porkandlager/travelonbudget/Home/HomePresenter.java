package com.porkandlager.travelonbudget.com.porkandlager.travelonbudget.Home;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tista on 11/11/16.
 */

public interface HomePresenter {

    void onGoButtonClicked(TextView budgetTextView);

    void onBudgetSliderChanged(TextView budgetTextView, int budget);

    void makeFullScreen(ActionBar actionBar, View controlsView);

}
