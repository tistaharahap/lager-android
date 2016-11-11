package com.porkandlager.travelonbudget;

import android.app.Application;

import com.porkandlager.travelonbudget.wires.retrofitter.ApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tista on 11/12/16.
 */

public class TravelOnBudgetApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        intializeRetrofit();
    }

    private void intializeRetrofit() {
        OkHttpClient client = new OkHttpClient();
//        client.interceptors().add(chain -> {
//            Request request = chain.request();
//            request = request.newBuilder()
//                    .addHeader("Accept", "application/json")
//                    .build();
//
//            return chain.proceed(request);
//        });

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://api.travelonbudget.co/v1/")
                .build();

        ApiService.INSTANCE.setRetrofit(retrofit);
    }

}
