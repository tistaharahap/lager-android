package com.porkandlager.travelonbudget.wires.retrofitter;

import com.porkandlager.travelonbudget.wires.models.acta.Acta;
import com.porkandlager.travelonbudget.wires.models.requests.FlightDetailImageRequestMeta;
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta;
import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tista on 11/12/16.
 */

public interface TravelOnBudgetService {

    @POST("events")
    Observable<FlightSearchWithBudgetResponse> getFlights(@Body Acta<FlightSearchRequestMeta> acta);

    @POST("events")
    Observable<FlightDetailImageSearchResponse> getPhotosFromAirport(@Body Acta<FlightDetailImageRequestMeta> acta);

}
