package com.porkandlager.travelonbudget.mvp.SearchResult;

import com.porkandlager.travelonbudget.wires.models.acta.Acta;
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

/**
 * Created by tista on 11/13/16.
 */

interface SearchResultInteractor {

    void searchFlights(String budget);
    void searchFlights(String budget, String outboundDate, String inboundDate);
    void onSearchResultSuccess(FlightSearchWithBudgetResponse response);
    Acta<FlightSearchRequestMeta> buildActaObject(String budget);
    Acta<FlightSearchRequestMeta> buildActaObject(String budget, String outboundDate, String inboundDate);

}
