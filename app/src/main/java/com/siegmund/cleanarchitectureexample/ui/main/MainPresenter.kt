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
    private var pageCount = 0
    private var currentPage = 0

    fun onVisible() = Thread {
        api.getTopRatedMovies(page = 1).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                currentPage = response.body().page
                pageCount = response.body().totalPages
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

    fun onRefreshPulled() = view?.refreshItems()

    fun onScrolledToEnd() = Thread {
        if (currentPage + 1 <= pageCount) {
            api.getTopRatedMovies(page = currentPage + 1).enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    currentPage = response.body().page
                    pageCount = response.body().totalPages
                    view?.addItems(response.body().results)
                }

                override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                    view?.showErrorMessage()
                }

            })
        }
    }.start()
}