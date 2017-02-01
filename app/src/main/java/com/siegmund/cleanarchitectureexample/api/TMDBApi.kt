package com.siegmund.cleanarchitectureexample.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Call<MovieResponse>
}