package com.porkandlager.travelonbudget.mvp.Home;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.porkandlager.travelonbudget.wires.Constants;
import com.porkandlager.travelonbudget.mvp.SearchResult.SearchResultActivity;
import com.porkandlager.travelonbudget.wires.Utils;

import java.text.NumberFormat;

/**
 * Created by tista on 11/11/16.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;
    private HomeInteractor homeInteractor;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        this.homeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void onGoButtonClicked(HomeView homeView, TextView budgetTextView) {
        Utils.LogV("GO Button Clicked, value: " + budgetTextView.getText());

        Intent intent = new Intent(homeView.getActivity(), SearchResultActivity.class);
        intent.putExtra(Constants.BUDGET_VALUE, budgetTextView.getText());

        homeView.getActivity().startActivity(intent);
    }

    @Override
    public void onBudgetSliderChanged(TextView budgetTextView, int budget) {
        budgetTextView.setText(String.format("IDR %s", NumberFormat.getInstance().format(budget)));
    }

    @Override
    public void makeFullScreen(ActionBar actionBar, View controlsView) {
        if(actionBar != null) {
            actionBar.hide();
        }

        if(controlsView != null) {
            controlsView.setVisibility(View.GONE);
        }
    }

}
