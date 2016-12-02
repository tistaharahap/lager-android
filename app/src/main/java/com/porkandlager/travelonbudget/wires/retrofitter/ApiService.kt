package com.porkandlager.travelonbudget.wires.retrofitter

import com.porkandlager.travelonbudget.wires.models.acta.Acta
import com.porkandlager.travelonbudget.wires.models.requests.FlightDetailImageRequestMeta
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta
import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse

import retrofit2.Retrofit
import rx.Observable

/**
 * Created by tista on 11/12/16.
 */

enum class ApiService {
    instance;

    private var travelOnBudgetService: TravelOnBudgetService? = null

    fun setRetrofit(retrofit: Retrofit) {
        this.travelOnBudgetService = retrofit.create(TravelOnBudgetService::class.java)
    }

    fun searchFlights(acta: Acta<FlightSearchRequestMeta>): Observable<FlightSearchWithBudgetResponse> {
        return travelOnBudgetService!!.getFlights(acta)
    }

    fun searchPhotos(acta: Acta<FlightDetailImageRequestMeta>): Observable<FlightDetailImageSearchResponse> {
        return travelOnBudgetService!!.getPhotosFromAirport(acta)
    }

}
