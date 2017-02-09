package com.siegmund.moviesapp.ui.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.siegmund.moviesapp.R
import com.siegmund.moviesapp.api.Movie
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.siegmund.moviesapp.App
import com.siegmund.moviesapp.BuildConfig
import com.siegmund.moviesapp.ui.credits.CreditsActivity
import com.siegmund.moviesapp.ui.details.DetailsActivity

class MainActivity: MvpActivity<MainView, MainPresenter>(), MainView {
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.swipeRefreshLayout) lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val adapter = MoviesAdapter { movie, position -> presenter.onItemClicked(movie, position) }

    override fun createPresenter() = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component().inject(presenter)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)

        if (BuildConfig.TMDB_API_KEY != "<your api key>") {
            val layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_span_count))
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
                override fun onLoadMore(currentPage: Int) {
                    presenter.onLoadMore()
                }
            })

            swipeRefreshLayout.setOnRefreshListener { presenter.onRefreshPulled() }
            swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_credits) {
            presenter.onCreditsClicked()
            return true
        } else return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        if (BuildConfig.TMDB_API_KEY != "<your api key>") {
            presenter.onVisible()
        } else {
            Toast.makeText(this, R.string.api_key_not_set, Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        presenter.onInvisible()
        super.onStop()
    }

    override fun setItems(movies: List<Movie>) {
        adapter.movies = movies.toMutableList()
    }

    override fun addItems(movies: List<Movie>) = adapter.addItems(movies)

    override fun refreshItems() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun openCreditsScreen() = startActivity(Intent(this, CreditsActivity::class.java))

    override fun openDetailsScreen(movie: Movie, position: Int) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(MOVIE_EXTRA, movie)
        }
        val view = recyclerView.findViewHolderForAdapterPosition(position).itemView
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "poster_image");
        startActivity(intent, options.toBundle());
    }

    override fun showErrorMessage() =
            Toast.makeText(this, R.string.error_message, Snackbar.LENGTH_LONG).show()

    companion object {
        val MOVIE_EXTRA = "com.siegmund.moviesapp.MOVIE"
    }
}
