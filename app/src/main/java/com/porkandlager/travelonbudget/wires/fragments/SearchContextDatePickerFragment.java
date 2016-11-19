package com.porkandlager.travelonbudget.wires.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.porkandlager.travelonbudget.mvp.SearchResult.SearchResultView;
import com.porkandlager.travelonbudget.wires.Constants;
import com.porkandlager.travelonbudget.wires.Utils;

/**
 * Created by tista on 11/19/16.
 */

public class SearchContextDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private boolean isOutbound;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();

        int year = arguments.getInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_YEAR);
        int month = arguments.getInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_MONTH);
        int day = arguments.getInt(Constants.SEARCH_DATE_PICKER_FRAGMENT_DAY);

        this.isOutbound = arguments.getBoolean(Constants.SEARCH_DATE_PICKER_FRAGMENT_IS_OUTBOUND);

        return new DatePickerDialog(getActivity(), this, year, month-1, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Activity activity = getActivity();
        if(!(activity instanceof SearchResultView)) {
            Utils.LogE("Trying to set date to an activity that is not an instance of SearchResultView. Bailing.");
            return;
        }

        SearchResultView searchResultView = (SearchResultView) activity;
        searchResultView.onDateSelected(this.isOutbound, year, monthOfYear+1, dayOfMonth);
    }

}
