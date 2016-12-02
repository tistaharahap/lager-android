package com.porkandlager.travelonbudget.mvp.SearchResult

import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse

/**
 * Created by tista on 11/13/16.
 */

internal class SearchResultPresenterImpl(private val searchResultView: SearchResultView) : SearchResultPresenter {
    private val interactor: SearchResultInteractor

    init {
        this.interactor = SearchResultInteractorImpl(this)
    }

    override fun searchFlights(budget: String) {
        var budget = budget
        budget = budget
                .replace(".", "")
                .replace(",", "")
                .replace("IDR ", "")

        interactor.searchFlights(budget)
    }

    override fun searchFlights(budget: String, outboundDate: String, inboundDate: String) {
        var budget = budget
        budget = budget
                .replace(".", "")
                .replace(",", "")
                .replace("IDR ", "")

        interactor.searchFlights(budget, outboundDate, inboundDate)
    }

    override fun onSearchResultSuccess(response: FlightSearchWithBudgetResponse) {
        searchResultView.initializeRecyclerView(response)
    }


}
