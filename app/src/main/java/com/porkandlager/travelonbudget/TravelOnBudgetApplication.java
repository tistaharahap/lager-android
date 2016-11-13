package com.porkandlager.travelonbudget;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.porkandlager.travelonbudget.wires.Utils;
import com.porkandlager.travelonbudget.wires.retrofitter.ApiService;

import io.fabric.sdk.android.Fabric;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
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
        Fabric.with(this, new Crashlytics());

        intializeRetrofit();
    }

    private void intializeRetrofit() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor())
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://api.travelonbudget.co/v1/")
                .build();

        ApiService.INSTANCE.setRetrofit(retrofit);
    }

    static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("User-Agent", "TravelOnBudget Android")
                    .build();

            long t1 = System.nanoTime();
            Utils.LogD(String.format(Locale.ENGLISH, "Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            if(request.body() != null)
                logRequestBody(request);

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Utils.LogD(String.format(Locale.ENGLISH, "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }

        private void logRequestBody(Request request) {
            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);

                Utils.LogD(buffer.readUtf8());
            } catch (final IOException e) {
                Utils.LogE("An error occurred while trying to log the request body");
            }
        }

    }

}
