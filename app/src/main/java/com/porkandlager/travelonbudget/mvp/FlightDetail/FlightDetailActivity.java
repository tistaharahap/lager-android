package com.porkandlager.travelonbudget.mvp.FlightDetail;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import com.porkandlager.travelonbudget.R;
import com.porkandlager.travelonbudget.wires.Utils;
import com.porkandlager.travelonbudget.wires.adapters.PhotosWithAttributionAdapter;
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution;

import java.text.NumberFormat;
import java.util.List;

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
    @BindView(R.id.flight_detail_photos_recyclerview) RecyclerView recyclerView;

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

        flightDetailPrice.setText(
                String.format("IDR %s",
                        NumberFormat.getInstance().format(flightDetail.getCheapestPrice())));
        flightDetailDates.setText(
                String.format("%s - %s",
                        flightDetail.getFlightDates().getOutboundFormattedShortMonthYear(),
                        flightDetail.getFlightDates().getInboundFormattedShortMonthYear()));

        bookNowButton.setOnClickListener(view -> presenter.bookNowClicked());
    }

    @Override
    public void populateMorePhotos(List<PhotoWithAttribution> photos) {
        if(photos == null) {
            Utils.LogE("Null flight search response");
            return;
        }

        Utils.LogV("Photos Size: " + photos.size());

        PhotosWithAttributionAdapter adapter = new PhotosWithAttributionAdapter(this);
        adapter.setPhotos(photos);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(adapter);

        ((ParallaxScrollView) mContentView).smoothScrollBy(0,
                getResources().getDimensionPixelSize(R.dimen.flight_detail_wrapper_top_spacing));
    }

}
