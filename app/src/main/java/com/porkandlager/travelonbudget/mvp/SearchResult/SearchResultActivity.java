package com.porkandlager.travelonbudget.mvp.SearchResult;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.porkandlager.travelonbudget.R;
import com.porkandlager.travelonbudget.wires.Constants;
import com.porkandlager.travelonbudget.wires.Utils;
import com.porkandlager.travelonbudget.wires.adapters.SearchResultAdapter;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends Activity implements SearchResultView {
    @BindView(R.id.search_result_recycler_view) RecyclerView searchResultRecyclerView;
    @BindView(R.id.fullscreen_content) View mContentView;
    @BindView(R.id.search_context_text) TextView searchContextText;

    private String budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        makeFullscreen();

        SearchResultPresenter presenter = new SearchResultPresenterImpl(this);

        budget = getIntent().getStringExtra(Constants.BUDGET_VALUE);
        presenter.searchFlights(budget);
    }

    @Override
    public void initializeRecyclerView(FlightSearchWithBudgetResponse response) {
        if(response == null) {
            Utils.LogE("Null flight search response");
            return;
        }
        searchResultRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        searchResultRecyclerView.setLayoutManager(linearLayoutManager);

        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this);
        searchResultAdapter.setFlights(response.getFlights());

        searchResultRecyclerView.setAdapter(searchResultAdapter);

        String outboundDate = humanizeDate(response.getFlights().get(0)
                .getFlightDates()
                .getOutbound());
        String inboundDate = humanizeDate(response.getFlights().get(0)
                .getFlightDates()
                .getInbound());

        String searchContextTextContent = String.format("%s - %s trip for %s",
                outboundDate, inboundDate, budget);
        searchContextText.setText(searchContextTextContent);
        searchContextText.setPaintFlags(searchContextText.getPaintFlags()
                | Paint.UNDERLINE_TEXT_FLAG);
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
    public String humanizeDate(String date) {
        String[] pieces = date.split("-");

        String shortMonth = "";
        switch(Integer.valueOf(pieces[1])) {
            case 1:
                shortMonth = "Jan";
                break;
            case 2:
                shortMonth = "Feb";
                break;
            case 3:
                shortMonth = "Mar";
                break;
            case 4:
                shortMonth = "Apr";
                break;
            case 5:
                shortMonth = "May";
                break;
            case 6:
                shortMonth = "Jun";
                break;
            case 7:
                shortMonth = "Jul";
                break;
            case 8:
                shortMonth = "Aug";
                break;
            case 9:
                shortMonth = "Sep";
                break;
            case 10:
                shortMonth = "Oct";
                break;
            case 11:
                shortMonth = "Nov";
                break;
            case 12:
                shortMonth = "Dec";
                break;
        }

        return String.format("%s %s", shortMonth, pieces[2]);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
