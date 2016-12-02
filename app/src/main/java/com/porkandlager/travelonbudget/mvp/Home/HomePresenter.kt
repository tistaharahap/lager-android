package com.porkandlager.travelonbudget.mvp.Home

import android.support.v7.app.ActionBar
import android.view.View
import android.widget.TextView

/**
 * Created by tista on 11/11/16.
 */

internal interface HomePresenter {

    fun onGoButtonClicked(homeView: HomeView, budgetTextView: TextView)
    fun onBudgetSliderChanged(budgetTextView: TextView, budget: Int)
    fun makeFullScreen(actionBar: ActionBar, controlsView: View)

}
