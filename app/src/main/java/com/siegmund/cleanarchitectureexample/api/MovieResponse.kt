package com.siegmund.cleanarchitectureexample.api

import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("page") val page: Int,
                         @SerializedName("total_results")val totalResults: Int,
                         @SerializedName("total_pages")val totalPages: Int,
                         @SerializedName("results")val results: List<Movie>)