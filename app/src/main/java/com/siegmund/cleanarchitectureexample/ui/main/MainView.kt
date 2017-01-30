package com.siegmund.cleanarchitectureexample.ui.main

import com.hannesdorfmann.mosby.mvp.MvpView
import com.siegmund.cleanarchitectureexample.api.Movie

interface MainView: MvpView {
    fun openCreditsScreen()
    fun setItems(movies: List<Movie>)
    fun openDetailsScreen(movie: Movie)
    fun showErrorMessage()
}