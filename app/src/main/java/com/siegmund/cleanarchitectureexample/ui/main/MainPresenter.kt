package com.siegmund.cleanarchitectureexample.ui.main

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.siegmund.cleanarchitectureexample.api.Movie
import com.siegmund.cleanarchitectureexample.api.MovieResponse
import com.siegmund.cleanarchitectureexample.api.TMDBApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainPresenter: MvpBasePresenter<MainView>() {
    @Inject lateinit var api: TMDBApi

    fun onVisible() = Thread {
        api.getTopRatedMovies().enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                view?.setItems(response.body().results)
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                view?.showErrorMessage()
            }

        })
    }.start()

    fun onInvisible() = Unit

    fun onCreditsClicked() = view?.openCreditsScreen()

    fun onItemClicked(movie: Movie) = view?.openDetailsScreen(movie)
}