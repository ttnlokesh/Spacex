package com.example.spacex.data.network.api

import android.content.Context
import android.content.Intent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

internal class HttpInterceptor(private val httpClient: OkHttpClient, private val context: Context) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //Build new request
        val builder = chain.request().newBuilder()
        val requestBody: RequestBody? = chain.request().body
        val subtype: String? = requestBody?.contentType()?.subtype
            builder.header(CONTENT_TYPE, APPLICATION_JSON)
        val request = builder.build() //overwrite old request
        val response = chain.proceed(request) //perform request, here original request will be executed
        return response
    }
}