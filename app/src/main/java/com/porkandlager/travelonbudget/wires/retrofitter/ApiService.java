package com.porkandlager.travelonbudget.wires.retrofitter;

import com.porkandlager.travelonbudget.wires.models.acta.Acta;
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by tista on 11/12/16.
 */

public enum ApiService {
    INSTANCE;

    private TravelOnBudgetService travelOnBudgetService;

    public void setRetrofit(Retrofit retrofit) {
        this.travelOnBudgetService = retrofit.create(TravelOnBudgetService.class);
    }

    public Observable<FlightSearchWithBudgetResponse> searchFlights(Acta<FlightSearchRequestMeta> acta) {
        return travelOnBudgetService.getFlights(acta);
    }

    public static ApiService getInstance() {
        return INSTANCE;
    }

}
