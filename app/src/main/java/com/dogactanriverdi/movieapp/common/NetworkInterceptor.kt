package com.dogactanriverdi.movieapp.common

import android.content.Context
import com.dogactanriverdi.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
        return chain.proceed(builder.build())
    }
}