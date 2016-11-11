package com.porkandlager.travelonbudget.com.porkandlager.travelonbudget.Home;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.porkandlager.travelonbudget.Utils;

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
    public void onGoButtonClicked(TextView budgetTextView) {
        Utils.LogV("GO Button Clicked, value: " + budgetTextView.getText());
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
        controlsView.setVisibility(View.GONE);
    }

}
