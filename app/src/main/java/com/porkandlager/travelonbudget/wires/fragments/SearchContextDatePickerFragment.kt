package com.porkandlager.travelonbudget.wires.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.widget.DatePicker
import com.porkandlager.travelonbudget.mvp.SearchResult.SearchResultView
import com.porkandlager.travelonbudget.wires.Constants
import com.porkandlager.travelonbudget.wires.Utils

/**
 * Created by tista on 11/19/16.
 */

class SearchContextDatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var isOutbound: Boolean = false

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val arguments = arguments

        val year = arguments.getInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_YEAR)
        val month = arguments.getInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_MONTH)
        val day = arguments.getInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_DAY)

        this.isOutbound = arguments.getBoolean(Constants.SEARCH_DATE_PICKER_FRAGMENT_IS_OUTBOUND)

        return DatePickerDialog(activity, this, year, month - 1, day)
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val activity = activity
        if (activity !is SearchResultView) {
            Utils.LogE("Trying to set date to an activity that is not an instance of SearchResultView. Bailing.")
            return
        }

        activity.onDateSelected(this.isOutbound, year, monthOfYear + 1, dayOfMonth)
    }

}
