package com.siegmund.cleanarchitectureexample.ui.details

import com.hannesdorfmann.mosby.mvp.MvpView

interface DetailsView: MvpView {
    fun configureUI(url: String, title: String, subtitle: String, description: String)
}