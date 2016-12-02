package com.porkandlager.travelonbudget.wires

import android.util.Log

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by tista on 11/11/16.
 */

object Utils {

    private val TAG = "TOB"

    fun LogV(message: String) {
        Log.v(TAG, message)
    }

    fun LogE(message: String) {
        Log.v(TAG, message)
    }

    fun LogD(message: String) {
        Log.d(Utils.TAG, message)
    }

    fun getDateFormattedShortMonth(theDate: String): String {
        var pattern = "yyyy-MM-dd"
        var simpleDateFormat: DateFormat = SimpleDateFormat(pattern, Locale.US)
        val date: Date

        try {
            date = simpleDateFormat.parse(theDate)
            pattern = "MMM d"
            simpleDateFormat = SimpleDateFormat(pattern, Locale.US)

            return simpleDateFormat.format(date)
        } catch (e: ParseException) {
            return theDate
        }

    }

    fun capitalizeFirstWords(string: String): String {
        val stringArray = string.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val builder = StringBuilder()
        for (s in stringArray) {
            val cap = s.substring(0, 1).toUpperCase() + s.substring(1)
            builder.append(cap)
            builder.append(" ")
        }

        return builder.toString()
    }

}
