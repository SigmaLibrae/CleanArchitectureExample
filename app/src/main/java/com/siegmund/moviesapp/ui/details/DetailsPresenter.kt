package com.siegmund.moviesapp.ui.details

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.siegmund.moviesapp.api.Movie
import com.siegmund.moviesapp.ui.util.asGermanDate

class DetailsPresenter(private val movie: Movie): MvpBasePresenter<DetailsView>() {
    fun onVisible() {
        view?.configureUI(
                url = movie.posterPath,
                title = movie.title,
                subtitle = movie.releaseDate.asGermanDate(),
                description = movie.overview
        )
    }

    fun onInvisible() = Unit
}