package com.porkandlager.travelonbudget;

import android.app.Application;

import com.porkandlager.travelonbudget.wires.retrofitter.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by tista on 11/12/16.
 */

public class TravelOnBudgetApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("https://api.travelonbudget.co/v1")
                .build();

        ApiService.INSTANCE.setRetrofit(retrofit);
    }

}
