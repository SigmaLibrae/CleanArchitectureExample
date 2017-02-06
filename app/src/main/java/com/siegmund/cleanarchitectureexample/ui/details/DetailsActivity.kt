package com.siegmund.cleanarchitectureexample.ui.details

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.siegmund.cleanarchitectureexample.R
import com.siegmund.cleanarchitectureexample.api.Movie
import com.siegmund.cleanarchitectureexample.ui.main.MainActivity

class DetailsActivity : MvpActivity<DetailsView, DetailsPresenter>(), DetailsView {
    @BindView(R.id.details_header_image) lateinit var headerImageView: ImageView
    @BindView(R.id.details_title) lateinit var titleTextView: TextView
    @BindView(R.id.details_subtitle) lateinit var subtitleTextView: TextView
    @BindView(R.id.details_description) lateinit var descriptionTextView: TextView

    override fun createPresenter() =
            DetailsPresenter(intent.getSerializableExtra(MainActivity.MOVIE_EXTRA) as Movie)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        ButterKnife.bind(this)
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
        Glide.with(this).load(uri).into(headerImageView)
        setTitle(title)
        titleTextView.text = title
        subtitleTextView.text = subtitle
        descriptionTextView.text = description
    }
}
