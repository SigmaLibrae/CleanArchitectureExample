package com.siegmund.cleanarchitectureexample.ui.credits

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.siegmund.cleanarchitectureexample.R

class CreditsActivity: MvpActivity<CreditsView, CreditsPresenter>() {
    override fun createPresenter() = CreditsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        setTitle(R.string.credits_screen_title)
    }
}
