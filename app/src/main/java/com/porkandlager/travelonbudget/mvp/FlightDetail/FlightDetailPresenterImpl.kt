package com.porkandlager.travelonbudget.mvp.FlightDetail

import android.app.AlertDialog
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater

import com.porkandlager.travelonbudget.R
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch
import com.porkandlager.travelonbudget.wires.models.responses.FlightDetailImageSearchResponse
import com.tfc.webviewer.ui.WebViewerActivity

/**
 * Created by tista on 11/15/16.
 */

internal class FlightDetailPresenterImpl(private val flightDetailView: FlightDetailView) : FlightDetailPresenter {

    private var flightDetail: FlightSearch? = null
    private val interactor: FlightDetailInteractor

    init {
        this.interactor = FlightDetailInteractorImpl(this)
    }

    override fun parseIntent(intent: Intent) {
        this.flightDetail = interactor.buildFlightSearchBeanFromIntent(intent)

        flightDetailView.populateViewsFromFlightDetail(this.flightDetail!!)
        interactor.requestImages(this.flightDetail!!)
    }

    override fun bookNowClicked() {
        val builder = AlertDialog.Builder(flightDetailView.activity)
        val inflater = flightDetailView.activity.layoutInflater

        val dialog = builder
                .setView(inflater.inflate(R.layout.dialog_redirecting, null))
                .setCancelable(false)
                .create()
        dialog.show()

        // Delay this to show the user the dialog content
        val handler = Handler()
        handler.postDelayed({
            if (dialog.isShowing) {
                dialog.cancel()
            }

            // Log first
            interactor.logBookNowClick(
                    flightDetail!!.airports!!.destination!!.city!!,
                    flightDetail!!.cheapestPrice.toString())

            val intent = Intent(flightDetailView.activity,
                    WebViewerActivity::class.java)
            intent.putExtra(WebViewerActivity.EXTRA_URL,
                    flightDetail!!.referralLink)
            flightDetailView.activity.startActivity(intent)
        }, 1500)
    }

    override fun showPhotosFromImageSearch(response: FlightDetailImageSearchResponse) {
        flightDetailView.populateMorePhotos(response.photos!!)
    }

}
