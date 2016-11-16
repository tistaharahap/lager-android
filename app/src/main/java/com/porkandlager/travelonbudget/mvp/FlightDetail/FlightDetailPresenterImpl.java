package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;

import com.porkandlager.travelonbudget.R;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(flightDetailView.getActivity());
        LayoutInflater inflater = flightDetailView.getActivity().getLayoutInflater();

        AlertDialog dialog = builder
                .setView(inflater.inflate(R.layout.dialog_redirecting, null))
                .setCancelable(false)
                .create();
        dialog.show();

        // Delay this to show the user the dialog content
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(dialog.isShowing()) {
                dialog.cancel();
            }

            Intent intent = new Intent(flightDetailView.getActivity(),
                    WebViewerActivity.class);
            intent.putExtra(WebViewerActivity.EXTRA_URL,
                    flightDetail.getReferralLink());
            flightDetailView.getActivity().startActivity(intent);
        }, 1500);
    }

    @Override
    public void showPhotosFromImageSearch(FlightDetailImageSearchResponse response) {
        flightDetailView.populateMorePhotos(response.getPhotos());
    }

}
