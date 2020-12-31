package com.porkandlager.travelonbudget.mvp.SearchResult

import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse

/**
 * Created by tista on 11/13/16.
 */

internal interface SearchResultPresenter {

    fun searchFlights(budget: String)
    fun searchFlights(budget: String, outboundDate: String, inboundDate: String)
    fun onSearchResultSuccess(response: FlightSearchWithBudgetResponse)

}
