package com.porkandlager.travelonbudget.mvp.SearchResult;

import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

/**
 * Created by tista on 11/13/16.
 */

interface SearchResultPresenter {

    void searchFlights(String budget);
    void searchFlights(String budget, String outboundDate, String inboundDate);
    void onSearchResultSuccess(FlightSearchWithBudgetResponse response);

}
