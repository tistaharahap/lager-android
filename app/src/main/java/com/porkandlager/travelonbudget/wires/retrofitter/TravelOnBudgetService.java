package com.porkandlager.travelonbudget.wires.retrofitter;

import com.porkandlager.travelonbudget.wires.models.acta.Acta;
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by tista on 11/12/16.
 */

public interface TravelOnBudgetService {

    @POST("/events")
    Call<FlightSearchWithBudgetResponse> getFlights(Acta<FlightSearchRequestMeta> acta);

}
