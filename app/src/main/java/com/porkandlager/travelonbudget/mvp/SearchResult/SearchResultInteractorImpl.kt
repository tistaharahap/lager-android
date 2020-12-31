package com.porkandlager.travelonbudget.mvp.SearchResult

import com.porkandlager.travelonbudget.wires.models.acta.Acta
import com.porkandlager.travelonbudget.wires.models.acta.ActaObj
import com.porkandlager.travelonbudget.wires.models.beans.FlightDates
import com.porkandlager.travelonbudget.wires.models.beans.Passengers
import com.porkandlager.travelonbudget.wires.models.requests.FlightSearchRequestMeta
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse
import com.porkandlager.travelonbudget.wires.retrofitter.ApiService

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by tista on 11/13/16.
 */

internal class SearchResultInteractorImpl(private val presenter: SearchResultPresenter) : SearchResultInteractor {

    override fun searchFlights(budget: String) {
        val acta = buildActaObject(budget)
        val flightSearch = ApiService.instance.searchFlights(acta)
        flightSearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn({ throwable -> null })
                .subscribe({ this.onSearchResultSuccess(it) })
    }

    override fun searchFlights(budget: String, outboundDate: String, inboundDate: String) {
        val acta = buildActaObject(budget, outboundDate, inboundDate)
        val flightSearch = ApiService.instance.searchFlights(acta)
        flightSearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn({ throwable -> null })
                .subscribe({ this.onSearchResultSuccess(it) })
    }

    override fun onSearchResultSuccess(response: FlightSearchWithBudgetResponse) {
        presenter.onSearchResultSuccess(response)
    }

    override fun buildActaObject(budget: String): Acta<FlightSearchRequestMeta> {
        val acta = Acta<FlightSearchRequestMeta>()

        val actor = ActaObj()
        actor.id = "@tista"
        actor.kind = "person"

        acta.actor = actor

        val obj = ActaObj()
        obj.id = "IDR-" + budget
        obj.kind = "currency-number"

        acta.`object` = obj

        acta.action = "flight-search-with-budget"

        val passengers = Passengers()
        passengers.adults = 1

        val meta = FlightSearchRequestMeta()
        meta.budget = budget
        meta.currency = "IDR"
        meta.passengers = passengers

        acta.meta = meta

        return acta
    }

    override fun buildActaObject(budget: String, outboundDate: String, inboundDate: String): Acta<FlightSearchRequestMeta> {
        val acta = Acta<FlightSearchRequestMeta>()

        val actor = ActaObj()
        actor.id = "@tista"
        actor.kind = "person"

        acta.actor = actor

        val obj = ActaObj()
        obj.id = "IDR-" + budget
        obj.kind = "currency-number"

        acta.`object` = obj

        acta.action = "flight-search-with-budget"

        val passengers = Passengers()
        passengers.adults = 1

        val meta = FlightSearchRequestMeta()
        meta.budget = budget
        meta.currency = "IDR"
        meta.passengers = passengers

        val dates = FlightDates()
        dates.outbound = outboundDate
        dates.inbound = inboundDate
        meta.dates = dates

        acta.meta = meta

        return acta
    }

}
