package com.porkandlager.travelonbudget.mvp.SearchResult;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        makeFullscreen();

        SearchResultPresenter presenter = new SearchResultPresenterImpl(this);

        String budget = getIntent().getStringExtra(Constants.BUDGET_VALUE);
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
