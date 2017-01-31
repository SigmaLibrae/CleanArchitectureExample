package com.siegmund.cleanarchitectureexample.ui.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.siegmund.cleanarchitectureexample.App
import com.siegmund.cleanarchitectureexample.R
import com.siegmund.cleanarchitectureexample.api.Movie
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.siegmund.cleanarchitectureexample.ui.credits.CreditsActivity
import com.siegmund.cleanarchitectureexample.ui.details.DetailsActivity

class MainActivity: MvpActivity<MainView, MainPresenter>(), MainView {
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.swipeRefreshLayout) lateinit var swipeRefreshLayout: SwipeRefreshLayout
    @BindView(R.id.fab) lateinit var fab: FloatingActionButton

    private val adapter = MoviesAdapter { movie -> presenter.onItemClicked(movie) }

    override fun createPresenter() = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component().inject(presenter)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
        fab.setOnClickListener { presenter.onFabClicked() }
        swipeRefreshLayout.setOnRefreshListener { presenter.onRefreshPulled() }
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)
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
        presenter.onVisible()
    }

    override fun onStop() {
        presenter.onInvisible()
        super.onStop()
    }

    override fun setItems(movies: List<Movie>) {
        adapter.movies = movies
    }

    override fun openCreditsScreen() = startActivity(Intent(this, CreditsActivity::class.java))

    override fun openDetailsScreen(movie: Movie) = startActivity(
            Intent(this, DetailsActivity::class.java).apply {
                putExtra(Companion.MOVIE_EXTRA, movie)
            }
    )

    override fun showErrorMessage() {
        Toast.makeText(this, R.string.error_message, Snackbar.LENGTH_LONG).show()
    }

    override fun refreshItems() {
        swipeRefreshLayout.isRefreshing = false
    }

    companion object {
        val MOVIE_EXTRA = "com.siegmund.cleanarchitectureexample.MOVIE"
    }
}
