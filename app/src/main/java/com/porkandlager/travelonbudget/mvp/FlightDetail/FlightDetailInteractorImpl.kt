package com.porkandlager.travelonbudget.mvp.FlightDetail

import android.content.Intent
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.porkandlager.travelonbudget.wires.Constants
import com.porkandlager.travelonbudget.wires.models.acta.Acta
import com.porkandlager.travelonbudget.wires.models.acta.ActaObj
import com.porkandlager.travelonbudget.wires.models.beans.*
import com.porkandlager.travelonbudget.wires.models.requests.FlightDetailImageRequestMeta
import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse
import com.porkandlager.travelonbudget.wires.retrofitter.ApiService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by tista on 11/15/16.
 */

internal class FlightDetailInteractorImpl(private val presenter: FlightDetailPresenter) : FlightDetailInteractor {

    override fun buildFlightSearchBeanFromIntent(intent: Intent): FlightSearch {
        val flightDetail = FlightSearch()

        val origin = Airport()
        origin.city = intent.getStringExtra(Constants.FLIGHT_DETAIL_AIRPORT_OUTBOUND)

        val destination = Airport()
        destination.city = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_CITY_NAME)
        destination.iataCode = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_IATA_CODE)

        val airports = Airports()
        airports.origin = origin
        airports.destination = destination

        flightDetail.airports = airports

        flightDetail.referralLink = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_REF_LINK)
        flightDetail.cheapestPrice = intent.getIntExtra(Constants.FLIGHT_DETAIL_EXTRAS_PRICE, 0)

        val flightDates = FlightDates()
        flightDates.outbound = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_OUTBOUND_DATE)
        flightDates.inbound = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_INBOUND_DATE)

        flightDetail.flightDates = flightDates

        val content = Content()
        content.description = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_OVERVIEW)
        content.pictureUrl = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_COVER_IMAGE_URL)
        content.wikipediaUrl = intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_WIKIPEDIA_URL)

        flightDetail.content = content

        return flightDetail
    }

    override fun requestImages(flightDetail: FlightSearch) {
        val acta = buildActaObjectForMoreImages(flightDetail
                .airports!!.destination!!.iataCode!!)

        ApiService.instance
                .searchPhotos(acta)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn { throwable -> null }
                .subscribe({ this.onImageSearchSuccess(it) })
    }

    override fun logBookNowClick(destination: String, price: String) {
        Answers.getInstance().logCustom(CustomEvent("Book Now Clicked")
                .putCustomAttribute("Destination", destination)
                .putCustomAttribute("Price", price))
    }

    private fun onImageSearchSuccess(response: FlightDetailImageSearchResponse) {
        presenter.showPhotosFromImageSearch(response)
    }

    private fun buildActaObjectForMoreImages(airportIataCode: String): Acta<FlightDetailImageRequestMeta> {
        val acta = Acta<FlightDetailImageRequestMeta>()

        val actor = ActaObj()
        actor.id = "@tista"
        actor.kind = "person"

        acta.actor = actor

        val obj = ActaObj()
        obj.id = airportIataCode
        obj.kind = "location"

        acta.`object` = obj

        acta.action = "image-search"

        val meta = FlightDetailImageRequestMeta()
        meta.airportIataCode = airportIataCode

        acta.meta = meta

        return acta
    }

}
