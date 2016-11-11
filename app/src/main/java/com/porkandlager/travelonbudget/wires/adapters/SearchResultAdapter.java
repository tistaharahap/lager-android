package com.porkandlager.travelonbudget.wires.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.porkandlager.travelonbudget.R;
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
        FlightSearch flight = flights.get(position);

        holder.searchResultDestination.setText(flight.getAirports().getDestination().getCity());
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_result_image) ImageView searchResultImage;
        @BindView(R.id.search_result_destination) TextView searchResultDestination;
        @BindView(R.id.search_result_price) TextView searchResultPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
