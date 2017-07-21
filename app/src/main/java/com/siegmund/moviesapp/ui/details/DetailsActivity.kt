package com.siegmund.moviesapp.ui.details

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.siegmund.moviesapp.R
import com.siegmund.moviesapp.api.Movie
import com.siegmund.moviesapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : MvpActivity<DetailsView, DetailsPresenter>(), DetailsView {
    override fun createPresenter() =
            DetailsPresenter(intent.getSerializableExtra(MainActivity.MOVIE_EXTRA) as Movie)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else              -> return false
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        presenter.onVisible()
    }

    override fun onStop() {
        presenter.onInvisible()
        super.onStop()
    }

    override fun configureUI(url: String, title: String, subtitle: String, description: String) {
        val uri = Uri.parse("http://image.tmdb.org/t/p/original/$url")
        Glide.with(this).load(uri).into(detailsHeaderImage)
        setTitle(title)
        detailsTitle.text = title
        detailsSubtitle.text = subtitle
        detailsDescription.text = description
    }
}
