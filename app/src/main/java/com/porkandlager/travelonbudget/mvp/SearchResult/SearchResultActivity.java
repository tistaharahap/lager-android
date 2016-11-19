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
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.porkandlager.travelonbudget.R;
import com.porkandlager.travelonbudget.wires.Constants;
import com.porkandlager.travelonbudget.wires.Utils;
import com.porkandlager.travelonbudget.wires.adapters.SearchResultAdapter;
import com.porkandlager.travelonbudget.wires.fragments.SearchContextDatePickerFragment;
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends Activity implements SearchResultView {
    @BindView(R.id.search_result_recycler_view) RecyclerView searchResultRecyclerView;
    @BindView(R.id.fullscreen_content) View mContentView;
    @BindView(R.id.search_context_text) TextView searchContextText;
    @BindView(R.id.search_options) TableLayout searchOptions;
    @BindView(R.id.outbound_date_value) TextView outboundDate;
    @BindView(R.id.inbound_date_value) TextView inboundDate;
    @BindView(R.id.budget_value) TextView budgetValue;
    @BindView(R.id.submit_button) Button submitButton;

    private String budget;
    private boolean searchOptionsShown = false;
    private SearchResultAdapter searchResultAdapter;
    private SearchResultPresenter presenter;

    private String outboundDateString, inboundDateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        makeFullscreen();

        presenter = new SearchResultPresenterImpl(this);

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

        searchResultAdapter = new SearchResultAdapter(this);
        searchResultAdapter.setFlights(response.getFlights());

        searchResultRecyclerView.setAdapter(searchResultAdapter);

        outboundDateString = response.getFlights().get(0)
                .getFlightDates()
                .getOutbound();
        inboundDateString = response.getFlights().get(0)
                .getFlightDates()
                .getInbound();

        String outboundDateHolder = humanizeDate(outboundDateString);
        String inboundDateHolder = humanizeDate(inboundDateString);

        String searchContextTextContent = String.format("%s - %s trip for %s",
                outboundDateHolder, inboundDateHolder, budget);
        searchContextText.setText(searchContextTextContent);
        searchContextText.setPaintFlags(searchContextText.getPaintFlags()
                | Paint.UNDERLINE_TEXT_FLAG);
        searchContextText.setOnClickListener(view -> {
            if(searchOptionsShown) {
                searchOptions.setVisibility(View.GONE);
            }
            else {
                searchOptions.setVisibility(View.VISIBLE);
            }

            searchOptionsShown = !searchOptionsShown;
        });

        budgetValue.setText(budget);

        this.outboundDate.setText(outboundDateHolder);
        this.outboundDate.setOnClickListener(view -> showDatepicker(response.getFlights().get(0)
                .getFlightDates()
                .getOutbound(), true));

        this.inboundDate.setText(inboundDateHolder);
        this.inboundDate.setOnClickListener(view -> showDatepicker(response.getFlights().get(0)
                .getFlightDates()
                .getInbound(), false));

        submitButton.setOnClickListener(view -> {
            // Hide the search options
            searchOptions.setVisibility(View.GONE);
            searchOptionsShown = !searchOptionsShown;

            // Empty the list first
            searchResultAdapter.setFlights(new ArrayList<>());
            searchResultAdapter.notifyDataSetChanged();

            presenter.searchFlights(budget, outboundDateString, inboundDateString);
        });
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
    public void showDatepicker(String currentDate, boolean isOutbound) {
        Utils.LogD("Current Date: " + currentDate);
        SearchContextDatePickerFragment fragment = new SearchContextDatePickerFragment();

        Bundle arguments = new Bundle();
        String[] dates = currentDate.split("-");
        arguments.putInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_YEAR,
                Integer.valueOf(dates[0]));
        arguments.putInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_MONTH,
                Integer.valueOf(dates[1]));
        arguments.putInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_DAY,
                Integer.valueOf(dates[2]));
        arguments.putBoolean(Constants.SEARCH_DATE_PICKER_FRAGMENT_IS_OUTBOUND,
                isOutbound);

        fragment.setArguments(arguments);
        fragment.show(getFragmentManager(),
                Constants.SEARCH_DATE_PICKER_FRAGMENT_TAG);
    }

    @Override
    public void onDateSelected(boolean isOutbound, int year, int month, int day) {
        String dateString = String.format("%s-%s-%s", year, month, day);

        if(isOutbound) {
            outboundDate.setText(humanizeDate(dateString));
            outboundDateString = dateString;

        }
        else {
            inboundDate.setText(humanizeDate(dateString));
            inboundDateString = dateString;
        }
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
