package com.siegmund.cleanarchitectureexample.api

import com.siegmund.cleanarchitectureexample.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class TMDBApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url().newBuilder().addQueryParameter("api_key", BuildConfig.TMDB_API_KEY).build()
        return chain.proceed(chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build())
    }
}