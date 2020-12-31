package com.porkandlager.travelonbudget.mvp.FlightDetail

import android.app.ActionBar
import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.crashlytics.android.answers.Answers
import com.nirhart.parallaxscroll.views.ParallaxScrollView
import com.porkandlager.travelonbudget.R
import com.porkandlager.travelonbudget.wires.Utils
import com.porkandlager.travelonbudget.wires.adapters.PhotosWithAttributionAdapter
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch
import com.porkandlager.travelonbudget.wires.models.beans.PhotoWithAttribution

import java.text.NumberFormat

import butterknife.BindView
import butterknife.ButterKnife
import io.fabric.sdk.android.Fabric

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FlightDetailActivity : Activity(), FlightDetailView {

    @BindView(R.id.fullscreen_content) internal var mContentView: View? = null
    @BindView(R.id.flight_detail_image) internal var flightDetailCoverImage: ImageView? = null
    @BindView(R.id.flight_detail_desc) internal var flightDetailDescription: TextView? = null
    @BindView(R.id.book_now_price) internal var flightDetailPrice: TextView? = null
    @BindView(R.id.book_now_dates) internal var flightDetailDates: TextView? = null
    @BindView(R.id.book_now_button) internal var bookNowButton: Button? = null
    @BindView(R.id.flight_detail_context) internal var flightDetailContext: TextView? = null
    @BindView(R.id.flight_detail_photos_recyclerview) internal var recyclerView: RecyclerView? = null

    private var presenter: FlightDetailPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_flight_detail)
        ButterKnife.bind(this)

        presenter = FlightDetailPresenterImpl(this)
        presenter!!.parseIntent(intent)

        Fabric.with(this, Answers())
    }

    override fun makeFullscreen() {
        val actionBar = actionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.hide()
        }
    }

    override fun getActivity(): Activity {
        return this
    }

    override fun populateViewsFromFlightDetail(flightDetail: FlightSearch) {
        Utils.LogV("Populating Flight Detail views")

        flightDetailContext!!.text = flightDetail.airports!!.destination!!.city

        Glide.with(this)
                .load(flightDetail.content!!.pictureUrl)
                .centerCrop()
                .into(flightDetailCoverImage!!)

        flightDetailDescription!!.text = flightDetail.content!!.description

        flightDetailPrice!!.text = String.format("IDR %s",
                NumberFormat.getInstance().format(flightDetail.cheapestPrice.toLong()))
        flightDetailDates!!.text = String.format("%s - %s",
                flightDetail.flightDates!!.outboundFormattedShortMonthYear,
                flightDetail.flightDates!!.inboundFormattedShortMonthYear)

        bookNowButton!!.setOnClickListener { view -> presenter!!.bookNowClicked() }
    }

    override fun populateMorePhotos(photos: List<PhotoWithAttribution>?) {
        if (photos == null) {
            Utils.LogE("Null flight search response")
            return
        }

        Utils.LogV("Photos Size: " + photos.size)

        val adapter = PhotosWithAttributionAdapter(this)
        adapter.setPhotos(photos)

        val layoutManager = StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL)

        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(recyclerView!!.context,
                layoutManager.orientation)
        recyclerView!!.addItemDecoration(itemDecoration)

        recyclerView!!.isNestedScrollingEnabled = false

        recyclerView!!.adapter = adapter

        (mContentView as ParallaxScrollView).smoothScrollBy(0,
                resources.getDimensionPixelSize(R.dimen.flight_detail_wrapper_top_spacing))
    }

}
