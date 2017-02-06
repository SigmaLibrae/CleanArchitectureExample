package com.siegmund.cleanarchitectureexample.ui.credits

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.siegmund.cleanarchitectureexample.R
import android.content.Intent
import android.net.Uri
import android.view.View

class CreditsActivity: MvpActivity<CreditsView, CreditsPresenter>(), CreditsView {
    override fun createPresenter() = CreditsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        setTitle(R.string.credits_screen_title)
    }

    fun onCreditsImageClicked(view: View) = presenter.onCreditsImageClicked()

    override fun openUrl(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}
