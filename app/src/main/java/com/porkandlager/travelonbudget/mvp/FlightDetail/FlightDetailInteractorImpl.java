package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.content.Intent;

import com.porkandlager.travelonbudget.wires.Constants;
import com.porkandlager.travelonbudget.wires.models.acta.Acta;
import com.porkandlager.travelonbudget.wires.models.acta.ActaObj;
import com.porkandlager.travelonbudget.wires.models.beans.Airport;
import com.porkandlager.travelonbudget.wires.models.beans.Airports;
import com.porkandlager.travelonbudget.wires.models.beans.Content;
import com.porkandlager.travelonbudget.wires.models.beans.FlightDates;
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;
import com.porkandlager.travelonbudget.wires.models.requests.FlightDetailImageRequestMeta;
import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse;
import com.porkandlager.travelonbudget.wires.retrofitter.ApiService;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tista on 11/15/16.
 */

class FlightDetailInteractorImpl implements FlightDetailInteractor {

    private FlightDetailPresenter presenter;

    FlightDetailInteractorImpl(FlightDetailPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public FlightSearch buildFlightSearchBeanFromIntent(Intent intent) {
        FlightSearch flightDetail = new FlightSearch();

        Airport origin = new Airport();
        origin.setCity(intent.getStringExtra(Constants.FLIGHT_DETAIL_AIRPORT_OUTBOUND));

        Airport destination = new Airport();
        destination.setCity(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_CITY_NAME));
        destination.setIataCode(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_IATA_CODE));

        Airports airports = new Airports();
        airports.setOrigin(origin);
        airports.setDestination(destination);

        flightDetail.setAirports(airports);

        flightDetail.setReferralLink(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_REF_LINK));
        flightDetail.setCheapestPrice(intent.getIntExtra(Constants.FLIGHT_DETAIL_EXTRAS_PRICE, 0));

        FlightDates flightDates = new FlightDates();
        flightDates.setOutbound(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_OUTBOUND_DATE));
        flightDates.setInbound(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_INBOUND_DATE));

        flightDetail.setFlightDates(flightDates);

        Content content = new Content();
        content.setDescription(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_OVERVIEW));
        content.setPictureUrl(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_COVER_IMAGE_URL));
        content.setWikipediaUrl(intent.getStringExtra(Constants.FLIGHT_DETAIL_EXTRAS_WIKIPEDIA_URL));

        flightDetail.setContent(content);

        return flightDetail;
    }

    @Override
    public void requestImages(FlightSearch flightDetail) {
        Acta<FlightDetailImageRequestMeta> acta = buildActaObjectForMoreImages(flightDetail
                .getAirports().getDestination().getIataCode());

        ApiService.getInstance()
                .searchPhotos(acta)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> null)
                .subscribe(this::onImageSearchSuccess);
    }

    private void onImageSearchSuccess(FlightDetailImageSearchResponse response) {
        presenter.showPhotosFromImageSearch(response);
    }

    private Acta<FlightDetailImageRequestMeta> buildActaObjectForMoreImages(String airportIataCode) {
        Acta<FlightDetailImageRequestMeta> acta = new Acta<>();

        ActaObj actor = new ActaObj();
        actor.setId("@tista");
        actor.setKind("person");

        acta.setActor(actor);

        ActaObj obj = new ActaObj();
        obj.setId(airportIataCode);
        obj.setKind("location");

        acta.setObject(obj);

        acta.setAction("image-search");

        FlightDetailImageRequestMeta meta = new FlightDetailImageRequestMeta();
        meta.setAirportIataCode(airportIataCode);

        acta.setMeta(meta);

        return acta;
    }

}
