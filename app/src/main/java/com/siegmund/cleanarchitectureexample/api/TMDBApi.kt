package com.siegmund.cleanarchitectureexample.api

import retrofit2.Call
import retrofit2.http.GET

interface TMDBApi {
    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("movie/latest")
    fun getLatestMovies(): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<MovieResponse>
}