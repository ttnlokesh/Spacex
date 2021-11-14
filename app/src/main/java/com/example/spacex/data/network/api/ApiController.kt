package com.example.spacex.data.network.api

import android.content.Context
import android.os.Build
import com.google.gson.GsonBuilder
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiController {

    //Returns an OkHttpClient instance used for building Retrofit service

    fun getBuilder(context: Context): OkHttpClient.Builder {
        val httpClient = addInterCepter(context)
//        httpClient.addNetworkInterceptor(HttpInterceptor(httpClient.build(), context))
        return httpClient
    }

// function to build our Retrofit service

    inline fun <reified T> createWebService(
        context: Context, baseUrl: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().setLenient().create()
                )
            )
            .client(getBuilder(context).build())
            .build()
        return retrofit.create(T::class.java)
    }

    private fun addInterCepter(context: Context): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        // Setting timeout
        httpClient.readTimeout(RETROFIT_SERVICE_TIMEOUT, TimeUnit.MILLISECONDS)
        httpClient.connectTimeout(RETROFIT_API_SERVICE_SOCKET_TIMEOUT, TimeUnit.MILLISECONDS)
        httpClient.followRedirects(true)
        httpClient.followSslRedirects(true)
        // add logging as last interceptor
        httpClient.addInterceptor(logging)  // <-- this is the important line!
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q
        ) {
            httpClient.addInterceptor(ChuckInterceptor(context))
            httpClient.addInterceptor(OkHttpProfilerInterceptor())
        }
        return httpClient
    }
}
