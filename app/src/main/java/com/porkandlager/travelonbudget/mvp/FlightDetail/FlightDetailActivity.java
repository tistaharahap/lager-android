package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.porkandlager.travelonbudget.R;
import com.porkandlager.travelonbudget.wires.Utils;
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FlightDetailActivity extends Activity implements FlightDetailView {

    @BindView(R.id.fullscreen_content) View mContentView;
    @BindView(R.id.flight_detail_image) ImageView flightDetailCoverImage;
    @BindView(R.id.flight_detail_desc) TextView flightDetailDescription;
    @BindView(R.id.book_now_price) TextView flightDetailPrice;
    @BindView(R.id.book_now_dates) TextView flightDetailDates;
    @BindView(R.id.book_now_button) Button bookNowButton;
    @BindView(R.id.flight_detail_context) TextView flightDetailContext;

    private FlightDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flight_detail);
        ButterKnife.bind(this);

        presenter = new FlightDetailPresenterImpl(this);
        presenter.parseIntent(getIntent());
    }

    @Override
    public void makeFullscreen() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
            else {
                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        }
        else {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.hide();
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void populateViewsFromFlightDetail(FlightSearch flightDetail) {
        Utils.LogV("Populating Flight Detail views");

        flightDetailContext.setText(flightDetail.getAirports().getDestination().getCity());

        Glide.with(this)
                .load(flightDetail.getContent().getPictureUrl())
                .centerCrop()
                .into(flightDetailCoverImage);

        flightDetailDescription.setText(flightDetail.getContent().getDescription());
        ((TextView) findViewById(R.id.flight_detail_desc2)).setText(flightDetail.getContent().getDescription());

        flightDetailPrice.setText(
                String.format("IDR %s",
                        NumberFormat.getInstance().format(flightDetail.getCheapestPrice())));
        flightDetailDates.setText(
                String.format("%s - %s",
                        flightDetail.getFlightDates().getOutboundFormattedShortMonthYear(),
                        flightDetail.getFlightDates().getInboundFormattedShortMonthYear()));

        bookNowButton.setOnClickListener(view -> {
            presenter.bookNowClicked();
        });
    }

}
