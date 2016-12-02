package com.porkandlager.travelonbudget.wires.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.porkandlager.travelonbudget.R
import com.porkandlager.travelonbudget.mvp.FlightDetail.FlightDetailActivity
import com.porkandlager.travelonbudget.wires.Constants
import com.porkandlager.travelonbudget.wires.Utils
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch

import java.text.NumberFormat

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by tista on 11/11/16.
 */

class SearchResultAdapter(private val context: Context) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private var flights: List<FlightSearch>? = null

    fun setFlights(flights: List<FlightSearch>) {
        this.flights = flights
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val searchResultView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_search_result, parent, false)

        return ViewHolder(searchResultView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flight = flights!![position]

        val destination = Utils.capitalizeFirstWords(String.format("%s, %s",
                flight.airports.destination.city,
                flight.airports.destination.country))

        holder.searchResultDestination!!.text = destination
        holder.searchResultPrice!!.text = String.format("IDR %s",
                NumberFormat.getInstance().format(flight.cheapestPrice.toLong()))

        Glide.with(context)
                .load(flight.content.pictureUrl)
                .centerCrop()
                .into(holder.searchResultImage!!)
    }

    override fun getItemCount(): Int {
        return this.flights!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.search_result_image) var searchResultImage: ImageView? = null
        @BindView(R.id.search_result_destination) var searchResultDestination: TextView? = null
        @BindView(R.id.search_result_price) var searchResultPrice: TextView? = null

        init {
            ButterKnife.bind(this, itemView)

            itemView.setOnClickListener { view ->
                val flight = flights!![adapterPosition]

                // Log first
                Answers.getInstance().logCustom(CustomEvent("Search Result Clicked")
                        .putCustomAttribute("Destination", flight.airports.destination.city)
                        .putCustomAttribute("Price", flight.cheapestPrice)
                        .putCustomAttribute("Outbound Date", flight.flightDates.outbound)
                        .putCustomAttribute("Inbound Date", flight.flightDates.inbound))

                // Start new activity
                val intent = Intent(context, FlightDetailActivity::class.java)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_IATA_CODE,
                        flight.airports.destination.iataCode)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_COVER_IMAGE_URL,
                        flight.content.pictureUrl)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_OVERVIEW,
                        flight.content.description)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_WIKIPEDIA_URL,
                        flight.content.wikipediaUrl)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_OUTBOUND_DATE,
                        flight.flightDates.outbound)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_INBOUND_DATE,
                        flight.flightDates.inbound)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_PRICE,
                        flight.cheapestPrice)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_CITY_NAME,
                        flight.airports.destination.city)
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_REF_LINK,
                        flight.referralLink)
                intent.putExtra(Constants.FLIGHT_DETAIL_AIRPORT_OUTBOUND,
                        flight.airports.destination.city)
                intent.putExtra(Constants.FLIGHT_DETAIL_AIRPORT_INBOUND,
                        flight.airports.origin.city)

                // Lift off
                context.startActivity(intent)
            }
        }

    }

}
