package com.siegmund.cleanarchitectureexample.ui.details

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.siegmund.cleanarchitectureexample.R

class DetailsActivity : MvpActivity<DetailsView, DetailsPresenter>() {
    override fun createPresenter() = DetailsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}
