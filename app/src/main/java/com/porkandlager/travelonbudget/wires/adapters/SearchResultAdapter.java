package com.porkandlager.travelonbudget.wires.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.porkandlager.travelonbudget.R;
import com.porkandlager.travelonbudget.mvp.FlightDetail.FlightDetailActivity;
import com.porkandlager.travelonbudget.wires.Constants;
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tista on 11/11/16.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<FlightSearch> flights;
    private Context context;

    public SearchResultAdapter(Context context) {
        this.context = context;
    }

    public void setFlights(List<FlightSearch> flights) {
        this.flights = flights;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View searchResultView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_search_result, parent, false);

        return new ViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FlightSearch flight = flights.get(position);

        String destination = String.format("%s, %s",
                flight.getAirports().getDestination().getCity(),
                flight.getAirports().getDestination().getCountry());

        holder.searchResultDestination.setText(destination);
        holder.searchResultPrice.setText(String.format("IDR %s",
                NumberFormat.getInstance().format(flight.getCheapestPrice())));

        Glide.with(context)
                .load(flight.getContent().getPictureUrl())
                .centerCrop()
                .into(holder.searchResultImage);
    }

    @Override
    public int getItemCount() {
        return this.flights.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_result_image) ImageView searchResultImage;
        @BindView(R.id.search_result_destination) TextView searchResultDestination;
        @BindView(R.id.search_result_price) TextView searchResultPrice;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> {
                FlightSearch flight = flights.get(getAdapterPosition());

                // Start new activity
                Intent intent = new Intent(context, FlightDetailActivity.class);
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_IATA_CODE,
                        flight.getAirports().getDestination().getIataCode());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_COVER_IMAGE_URL,
                        flight.getContent().getPictureUrl());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_OVERVIEW,
                        flight.getContent().getDescription());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_WIKIPEDIA_URL,
                        flight.getContent().getWikipediaUrl());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_OUTBOUND_DATE,
                        flight.getFlightDates().getOutbound());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_INBOUND_DATE,
                        flight.getFlightDates().getInbound());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_PRICE,
                        flight.getCheapestPrice());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_CITY_NAME,
                        flight.getAirports().getDestination().getCity());
                intent.putExtra(Constants.FLIGHT_DETAIL_EXTRAS_REF_LINK,
                        flight.getReferralLink());
                intent.putExtra(Constants.FLIGHT_DETAIL_AIRPORT_OUTBOUND,
                        flight.getAirports().getDestination().getCity());
                intent.putExtra(Constants.FLIGHT_DETAIL_AIRPORT_INBOUND,
                        flight.getAirports().getOrigin().getCity());

                // Lift off
                context.startActivity(intent);
            });
        }

    }

}
