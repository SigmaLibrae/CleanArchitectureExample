package com.siegmund.cleanarchitectureexample.ui.main

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private val layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {
    private var loading = true
    private var previousTotal = 0
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_TRESHOLD) {
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)

    companion object {
        val VISIBLE_TRESHOLD = 5
    }
}