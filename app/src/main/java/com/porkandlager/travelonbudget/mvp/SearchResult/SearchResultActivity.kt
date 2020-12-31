package com.porkandlager.travelonbudget.mvp.SearchResult

import android.app.ActionBar
import android.app.Activity
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView

import com.crashlytics.android.answers.Answers
import com.porkandlager.travelonbudget.R
import com.porkandlager.travelonbudget.wires.Constants
import com.porkandlager.travelonbudget.wires.Utils
import com.porkandlager.travelonbudget.wires.adapters.SearchResultAdapter
import com.porkandlager.travelonbudget.wires.fragments.SearchContextDatePickerFragment
import com.porkandlager.travelonbudget.wires.models.responses.FlightSearchWithBudgetResponse

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import com.porkandlager.travelonbudget.wires.models.beans.FlightSearch
import io.fabric.sdk.android.Fabric

class SearchResultActivity : Activity(), SearchResultView {
    @BindView(R.id.search_result_recycler_view) internal var searchResultRecyclerView: RecyclerView? = null
    @BindView(R.id.fullscreen_content) internal var mContentView: View? = null
    @BindView(R.id.search_context_text) internal var searchContextText: TextView? = null
    @BindView(R.id.search_options) internal var searchOptions: TableLayout? = null
    @BindView(R.id.outbound_date_value) internal var outboundDate: TextView? = null
    @BindView(R.id.inbound_date_value) internal var inboundDate: TextView? = null
    @BindView(R.id.budget_value) internal var budgetValue: TextView? = null
    @BindView(R.id.submit_button) internal var submitButton: Button? = null

    private var budget: String? = null
    private var searchOptionsShown = false
    private var searchResultAdapter: SearchResultAdapter? = null
    private var presenter: SearchResultPresenter? = null

    private var outboundDateString: String? = null
    private var inboundDateString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search_result)
        ButterKnife.bind(this)

        makeFullscreen()

        presenter = SearchResultPresenterImpl(this)

        budget = intent.getStringExtra(Constants.BUDGET_VALUE)
        presenter!!.searchFlights(budget)

        Fabric.with(this, Answers())
    }

    override fun initializeRecyclerView(response: FlightSearchWithBudgetResponse?) {
        if (response == null) {
            Utils.LogE("Null flight search response")
            return
        }
        searchResultRecyclerView!!.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        searchResultRecyclerView!!.layoutManager = linearLayoutManager

        searchResultAdapter = SearchResultAdapter(this)
        searchResultAdapter!!.setFlights(response.flights!!)

        searchResultRecyclerView!!.adapter = searchResultAdapter

        outboundDateString = response.flights[0]
                .flightDates!!
                .outbound
        inboundDateString = response.flights[0]
                .flightDates!!
                .inbound

        val outboundDateHolder = humanizeDate(outboundDateString!!)
        val inboundDateHolder = humanizeDate(inboundDateString!!)

        val searchContextTextContent = String.format("%s - %s trip for %s",
                outboundDateHolder, inboundDateHolder, budget)
        searchContextText!!.text = searchContextTextContent
        searchContextText!!.paintFlags = searchContextText!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        searchContextText!!.setOnClickListener { view ->
            if (searchOptionsShown) {
                searchOptions!!.visibility = View.GONE
            } else {
                searchOptions!!.visibility = View.VISIBLE
            }

            searchOptionsShown = !searchOptionsShown
        }

        budgetValue!!.text = budget

        this.outboundDate!!.text = outboundDateHolder
        this.outboundDate!!.setOnClickListener { view ->
            showDatepicker(response.flights[0]
                    .flightDates!!
                    .outbound!!, true)
        }

        this.inboundDate!!.text = inboundDateHolder
        this.inboundDate!!.setOnClickListener { view ->
            showDatepicker(response.flights[0]
                    .flightDates!!
                    .inbound!!, false)
        }

        submitButton!!.setOnClickListener { view ->
            // Hide the search options
            searchOptions!!.visibility = View.GONE
            searchOptionsShown = !searchOptionsShown

            // Empty the list first
            searchResultAdapter!!.setFlights(ArrayList<FlightSearch>())
            searchResultAdapter!!.notifyDataSetChanged()

            presenter!!.searchFlights(budget, outboundDateString, inboundDateString)
        }
    }

    override fun makeFullscreen() {
        val actionBar = actionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.hide()
        }
    }

    override fun humanizeDate(date: String): String {
        val pieces = date.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        var shortMonth = ""
        when (Integer.valueOf(pieces[1])) {
            1 -> shortMonth = "Jan"
            2 -> shortMonth = "Feb"
            3 -> shortMonth = "Mar"
            4 -> shortMonth = "Apr"
            5 -> shortMonth = "May"
            6 -> shortMonth = "Jun"
            7 -> shortMonth = "Jul"
            8 -> shortMonth = "Aug"
            9 -> shortMonth = "Sep"
            10 -> shortMonth = "Oct"
            11 -> shortMonth = "Nov"
            12 -> shortMonth = "Dec"
        }

        return String.format("%s %s", shortMonth, pieces[2])
    }

    override fun getActivity(): Activity {
        return this
    }

    override fun showDatepicker(currentDate: String, isOutbound: Boolean) {
        Utils.LogD("Current Date: " + currentDate)
        val fragment = SearchContextDatePickerFragment()

        val arguments = Bundle()
        val dates = currentDate.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        arguments.putInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_YEAR,
                Integer.valueOf(dates[0])!!)
        arguments.putInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_MONTH,
                Integer.valueOf(dates[1])!!)
        arguments.putInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_DAY,
                Integer.valueOf(dates[2])!!)
        arguments.putBoolean(Constants.SEARCH_DATE_PICKER_FRAGMENT_IS_OUTBOUND,
                isOutbound)

        fragment.arguments = arguments
        fragment.show(fragmentManager,
                Constants.SEARCH_DATE_PICKER_FRAGMENT_TAG)
    }

    override fun onDateSelected(isOutbound: Boolean, year: Int, month: Int, day: Int) {
        val dateString = String.format("%s-%s-%s", year, month, day)

        if (isOutbound) {
            outboundDate!!.text = humanizeDate(dateString)
            outboundDateString = dateString

        } else {
            inboundDate!!.text = humanizeDate(dateString)
            inboundDateString = dateString
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
