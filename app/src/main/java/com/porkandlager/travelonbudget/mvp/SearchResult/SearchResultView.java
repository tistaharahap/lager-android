package com.porkandlager.travelonbudget.mvp.SearchResult;

import android.app.Activity;

import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

/**
 * Created by tista on 11/13/16.
 */

public interface SearchResultView {

    void initializeRecyclerView(FlightSearchWithBudgetResponse response);
    String humanizeDate(String date);
    void makeFullscreen();
    Activity getActivity();
    void showDatepicker(String currentDate, boolean isOutbound);
    void onDateSelected(boolean isOutbound, int year, int month, int day);

}
