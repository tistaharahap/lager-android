package com.porkandlager.travelonbudget.mvp.SearchResult;

import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

/**
 * Created by tista on 11/13/16.
 */

class SearchResultPresenterImpl implements SearchResultPresenter {

    private SearchResultView searchResultView;
    private SearchResultInteractor interactor;

    SearchResultPresenterImpl(SearchResultView searchResultView) {
        this.searchResultView = searchResultView;
        this.interactor = new SearchResultInteractorImpl(this);
    }

    @Override
    public void searchFlights(String budget) {
        budget = budget
                .replace(".", "")
                .replace(",", "")
                .replace("IDR ", "");

        interactor.searchFlights(budget);
    }

    @Override
    public void searchFlights(String budget, String outboundDate, String inboundDate) {
        budget = budget
                .replace(".", "")
                .replace(",", "")
                .replace("IDR ", "");

        interactor.searchFlights(budget ,outboundDate, inboundDate);
    }

    @Override
    public void onSearchResultSuccess(FlightSearchWithBudgetResponse response) {
        searchResultView.initializeRecyclerView(response);
    }



}
