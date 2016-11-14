package com.porkandlager.travelonbudget.mvp.SearchResult;

import android.app.Activity;

import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

/**
 * Created by tista on 11/13/16.
 */

public interface SearchResultView {

    void initializeRecyclerView(FlightSearchWithBudgetResponse response);
    void makeFullscreen();
    String humanizeDate(String date);
    Activity getActivity();

}
