package com.porkandlager.travelonbudget.mvp.SearchResult;

import com.porkandlager.travelonbudget.wires.models.acta.Acta;
import com.porkandlager.travelonbudget.wires.models.acta.ActaObj;
import com.porkandlager.travelonbudget.wires.models.beans.FlightDates;
import com.porkandlager.travelonbudget.wires.models.beans.Passengers;
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;
import com.porkandlager.travelonbudget.wires.retrofitter.ApiService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tista on 11/13/16.
 */

class SearchResultInteractorImpl implements SearchResultInteractor {

    private SearchResultPresenter presenter;

    SearchResultInteractorImpl(SearchResultPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void searchFlights(String budget) {
        Acta<FlightSearchRequestMeta> acta = buildActaObject(budget);
        Observable<FlightSearchWithBudgetResponse> flightSearch =
                ApiService.getInstance().searchFlights(acta);
        flightSearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> null)
                .subscribe(this::onSearchResultSuccess);
    }

    @Override
    public void searchFlights(String budget, String outboundDate, String inboundDate) {
        Acta<FlightSearchRequestMeta> acta = buildActaObject(budget, outboundDate, inboundDate);
        Observable<FlightSearchWithBudgetResponse> flightSearch =
                ApiService.getInstance().searchFlights(acta);
        flightSearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> null)
                .subscribe(this::onSearchResultSuccess);
    }

    @Override
    public void onSearchResultSuccess(FlightSearchWithBudgetResponse response) {
        presenter.onSearchResultSuccess(response);
    }

    @Override
    public Acta<FlightSearchRequestMeta> buildActaObject(String budget) {
        Acta<FlightSearchRequestMeta> acta = new Acta<>();

        ActaObj actor = new ActaObj();
        actor.setId("@tista");
        actor.setKind("person");

        acta.setActor(actor);

        ActaObj obj = new ActaObj();
        obj.setId("IDR-" + budget);
        obj.setKind("currency-number");

        acta.setObject(obj);

        acta.setAction("flight-search-with-budget");

        Passengers passengers = new Passengers();
        passengers.setAdults(1);

        FlightSearchRequestMeta meta = new FlightSearchRequestMeta();
        meta.setBudget(budget);
        meta.setCurrency("IDR");
        meta.setPassengers(passengers);

        acta.setMeta(meta);

        return acta;
    }

    @Override
    public Acta<FlightSearchRequestMeta> buildActaObject(String budget, String outboundDate, String inboundDate) {
        Acta<FlightSearchRequestMeta> acta = new Acta<>();

        ActaObj actor = new ActaObj();
        actor.setId("@tista");
        actor.setKind("person");

        acta.setActor(actor);

        ActaObj obj = new ActaObj();
        obj.setId("IDR-" + budget);
        obj.setKind("currency-number");

        acta.setObject(obj);

        acta.setAction("flight-search-with-budget");

        Passengers passengers = new Passengers();
        passengers.setAdults(1);

        FlightSearchRequestMeta meta = new FlightSearchRequestMeta();
        meta.setBudget(budget);
        meta.setCurrency("IDR");
        meta.setPassengers(passengers);

        FlightDates dates = new FlightDates();
        dates.setOutbound(outboundDate);
        dates.setInbound(inboundDate);
        meta.setDates(dates);

        acta.setMeta(meta);

        return acta;
    }

}
