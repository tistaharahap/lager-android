package com.porkandlager.travelonbudget.wires.retrofitter;

import com.porkandlager.travelonbudget.wires.models.acta.Acta;
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by tista on 11/12/16.
 */

public enum ApiService {
    INSTANCE;

    private TravelOnBudgetService travelOnBudgetService;

    public void setRetrofit(Retrofit retrofit) {
        this.travelOnBudgetService = retrofit.create(TravelOnBudgetService.class);
    }

    public void searchFlights(Acta<FlightSearchRequestMeta> acta, Callback<FlightSearchWithBudgetResponse> callback) {
        Call<FlightSearchWithBudgetResponse> call = travelOnBudgetService.getFlights(acta);
        call.enqueue(callback);
    }

}
