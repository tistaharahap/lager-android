package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.content.Intent;
import android.net.Uri;

import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;

/**
 * Created by tista on 11/15/16.
 */

class FlightDetailPresenterImpl implements FlightDetailPresenter {

    private FlightSearch flightDetail;
    private FlightDetailView flightDetailView;
    private FlightDetailInteractor interactor;

    FlightDetailPresenterImpl(FlightDetailView flightDetailView) {
        this.flightDetailView = flightDetailView;
        this.interactor = new FlightDetailInteractorImpl();
    }

    @Override
    public void parseIntent(Intent intent) {
        this.flightDetail = interactor.buildFlightSearchBeanFromIntent(intent);

        flightDetailView.populateViewsFromFlightDetail(this.flightDetail);
    }

    @Override
    public void bookNowClicked() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(flightDetail.getReferralLink()));
        flightDetailView.getActivity().startActivity(intent);
    }

}
