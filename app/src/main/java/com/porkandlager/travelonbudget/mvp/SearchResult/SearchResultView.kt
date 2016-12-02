package com.porkandlager.travelonbudget.mvp.SearchResult

import android.app.Activity

import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse

/**
 * Created by tista on 11/13/16.
 */

interface SearchResultView {

    fun initializeRecyclerView(response: FlightSearchWithBudgetResponse)
    fun humanizeDate(date: String): String
    fun makeFullscreen()
    val activity: Activity
    fun showDatepicker(currentDate: String, isOutbound: Boolean)
    fun onDateSelected(isOutbound: Boolean, year: Int, month: Int, day: Int)

}
