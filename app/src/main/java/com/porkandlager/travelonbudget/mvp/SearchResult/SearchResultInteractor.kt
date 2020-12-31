package com.porkandlager.travelonbudget.mvp.SearchResult

import com.porkandlager.travelonbudget.wires.models.acta.Acta
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse

/**
 * Created by tista on 11/13/16.
 */

internal interface SearchResultInteractor {

    fun searchFlights(budget: String)
    fun searchFlights(budget: String, outboundDate: String, inboundDate: String)
    fun onSearchResultSuccess(response: FlightSearchWithBudgetResponse)
    fun buildActaObject(budget: String): Acta<FlightSearchRequestMeta>
    fun buildActaObject(budget: String, outboundDate: String, inboundDate: String): Acta<FlightSearchRequestMeta>

}
