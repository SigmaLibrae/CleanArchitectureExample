package com.siegmund.moviesapp.ui.credits

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter

class CreditsPresenter: MvpBasePresenter<CreditsView>() {
    fun onCreditsImageClicked() {
        view?.openUrl(TMDB_URL)
    }

    companion object {
        private val TMDB_URL = "https://www.themoviedb.org/"
    }
}