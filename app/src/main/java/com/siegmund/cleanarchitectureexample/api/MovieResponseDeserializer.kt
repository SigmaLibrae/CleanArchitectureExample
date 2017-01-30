package com.siegmund.cleanarchitectureexample.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MovieResponseDeserializer : JsonDeserializer<MovieResponse> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): MovieResponse {
        val movieResponseObject = json.asJsonObject
        val gson = Gson()
        val movieResponse = gson.fromJson(json, MovieResponse::class.java)
        val movies: List<Movie>
        if (movieResponseObject.get("results").isJsonArray) {
            movies = gson.fromJson(movieResponseObject.get("results"), object: TypeToken<List<Movie>>() {}.type)
        } else {
            val movie = gson.fromJson(movieResponseObject.get("results"), Movie::class.java)
            movies = arrayListOf(movie)
        }

        return movieResponse.copy(results = movies)
    }
}