package com.porkandlager.travelonbudget.wires;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tista on 11/11/16.
 */

public class Utils {

    private static final String TAG = "TOB";

    public static void LogV(String message) {
        Log.v(TAG, message);
    }

    public static void LogE(String message) {
        Log.v(TAG, message);
    }

    public static void LogD(String message) {
        Log.d(Utils.TAG, message);
    }

    public static String getDateFormattedShortMonth(String theDate) {
        String pattern = "yyyy-MM-dd";
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);
        Date date;

        try {
            date = simpleDateFormat.parse(theDate);
            pattern = "MMM d";
            simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);

            return simpleDateFormat.format(date);
        }
        catch(ParseException e) {
            return theDate;
        }
    }

    public static String capitalizeFirstWords(String string) {
        String[] stringArray = string.split(" ");
        StringBuilder builder = new StringBuilder();
        for(String s : stringArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap);
            builder.append(" ");
        }

        return builder.toString();
    }

}
