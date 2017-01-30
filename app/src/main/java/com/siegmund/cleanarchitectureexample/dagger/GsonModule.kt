package com.siegmund.cleanarchitectureexample.dagger

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.siegmund.cleanarchitectureexample.api.MovieResponseDeserializer
import com.siegmund.cleanarchitectureexample.api.MovieResponse
import dagger.Module
import dagger.Provides

@Module
class GsonModule {
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()/*.registerTypeAdapter(MovieResponse::class.java, MovieResponseDeserializer())*/.create()
    }
}