package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.content.Intent;

import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;
import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse;
import com.tfc.webviewer.ui.WebViewerActivity;

/**
 * Created by tista on 11/15/16.
 */

class FlightDetailPresenterImpl implements FlightDetailPresenter {

    private FlightSearch flightDetail;
    private FlightDetailView flightDetailView;
    private FlightDetailInteractor interactor;

    FlightDetailPresenterImpl(FlightDetailView flightDetailView) {
        this.flightDetailView = flightDetailView;
        this.interactor = new FlightDetailInteractorImpl(this);
    }

    @Override
    public void parseIntent(Intent intent) {
        this.flightDetail = interactor.buildFlightSearchBeanFromIntent(intent);

        flightDetailView.populateViewsFromFlightDetail(this.flightDetail);
        interactor.requestImages(this.flightDetail);
    }

    @Override
    public void bookNowClicked() {
        Intent intent = new Intent(flightDetailView.getActivity(),
                WebViewerActivity.class);
        intent.putExtra(WebViewerActivity.EXTRA_URL,
                flightDetail.getReferralLink());
        flightDetailView.getActivity().startActivity(intent);
    }

    @Override
    public void showPhotosFromImageSearch(FlightDetailImageSearchResponse response) {
        flightDetailView.populateMorePhotos(response.getPhotos());
    }

}
