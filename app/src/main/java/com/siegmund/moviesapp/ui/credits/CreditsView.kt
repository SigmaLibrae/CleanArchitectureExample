package com.siegmund.moviesapp.ui.credits

import com.hannesdorfmann.mosby.mvp.MvpView

interface CreditsView: MvpView {
    fun openUrl(url: String)
}