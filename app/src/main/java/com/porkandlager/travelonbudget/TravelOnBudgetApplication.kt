package com.porkandlager.travelonbudget

import android.app.Application

import com.crashlytics.android.Crashlytics
import com.porkandlager.travelonbudget.wires.Utils
import com.porkandlager.travelonbudget.wires.retrofitter.ApiService

import io.fabric.sdk.android.Fabric
import java.io.IOException
import java.util.Locale
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by tista on 11/12/16.
 */

class TravelOnBudgetApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())

        intializeRetrofit()
    }

    private fun intializeRetrofit() {

        val client = OkHttpClient.Builder()
                .addNetworkInterceptor(LoggingInterceptor())
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://api.travelonbudget.co/v1/")
                .build()

        ApiService.instance.setRetrofit(retrofit)
    }

    internal class LoggingInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("User-Agent", "TravelOnBudget Android")
                    .build()

            val t1 = System.nanoTime()
            Utils.LogD(String.format(Locale.ENGLISH, "Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()))
            if (request.body() != null)
                logRequestBody(request)

            val response = chain.proceed(request)

            val t2 = System.nanoTime()
            Utils.LogD(String.format(Locale.ENGLISH, "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6, response.headers()))

            return response
        }

        private fun logRequestBody(request: Request) {
            try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                copy.body().writeTo(buffer)

                Utils.LogD(buffer.readUtf8())
            } catch (e: IOException) {
                Utils.LogE("An error occurred while trying to log the request body")
            }

        }

    }

}
