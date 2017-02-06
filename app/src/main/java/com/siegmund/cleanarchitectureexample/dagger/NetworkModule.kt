package com.siegmund.cleanarchitectureexample.dagger

import com.google.gson.Gson
import com.siegmund.cleanarchitectureexample.api.TMDBApi
import com.siegmund.cleanarchitectureexample.api.TMDBApiInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(GsonModule::class))
class NetworkModule {
    @Provides @Singleton
    fun provideApi(gson: Gson): TMDBApi {
        val apiClient = OkHttpClient.Builder().addInterceptor(TMDBApiInterceptor()).build()

        return Retrofit.Builder().apply {
            baseUrl(TMDBApi.ENDPOINT)
            addConverterFactory(GsonConverterFactory.create(gson))
            client(apiClient)
        }.build().create(TMDBApi::class.java)
    }
}