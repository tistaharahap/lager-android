package com.porkandlager.travelonbudget.mvp.Home

import android.content.Intent
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.TextView

import com.porkandlager.travelonbudget.wires.Constants
import com.porkandlager.travelonbudget.mvp.SearchResult.SearchResultActivity
import com.porkandlager.travelonbudget.wires.Utils

import java.text.NumberFormat

/**
 * Created by tista on 11/11/16.
 */

class HomePresenterImpl(private val homeView: HomeView) : HomePresenter {
    private val homeInteractor: HomeInteractor

    init {
        this.homeInteractor = HomeInteractorImpl()
    }

    override fun onGoButtonClicked(homeView: HomeView, budgetTextView: TextView) {
        Utils.LogV("GO Button Clicked, value: " + budgetTextView.text)

        // Log first
        homeInteractor.logGoButtonClicked(
                budgetTextView.text.toString())

        val intent = Intent(homeView.activity, SearchResultActivity::class.java)
        intent.putExtra(Constants.BUDGET_VALUE, budgetTextView.text)

        homeView.activity.startActivity(intent)
    }

    override fun onBudgetSliderChanged(budgetTextView: TextView, budget: Int) {
        budgetTextView.text = String.format("IDR %s", NumberFormat.getInstance().format(budget.toLong()))
    }

    override fun makeFullScreen(actionBar: ActionBar, controlsView: View) {
        actionBar?.hide()

        if (controlsView != null) {
            controlsView.visibility = View.GONE
        }
    }

}
