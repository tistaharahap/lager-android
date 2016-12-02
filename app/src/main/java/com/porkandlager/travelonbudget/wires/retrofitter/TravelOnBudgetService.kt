package com.porkandlager.travelonbudget.wires.retrofitter

import com.porkandlager.travelonbudget.wires.models.acta.Acta
import com.porkandlager.travelonbudget.wires.models.requests.FlightDetailImageRequestMeta
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta
import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse

import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by tista on 11/12/16.
 */

interface TravelOnBudgetService {

    @POST("events")
    fun getFlights(@Body acta: Acta<FlightSearchRequestMeta>): Observable<FlightSearchWithBudgetResponse>

    @POST("events")
    fun getPhotosFromAirport(@Body acta: Acta<FlightDetailImageRequestMeta>): Observable<FlightDetailImageSearchResponse>

}
