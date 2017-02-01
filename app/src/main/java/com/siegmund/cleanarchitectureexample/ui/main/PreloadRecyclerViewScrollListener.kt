package com.siegmund.cleanarchitectureexample.ui.main

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class PreloadRecyclerViewScrollListener(private val layoutManager: GridLayoutManager,
                                        private val preloadCallback: () -> Unit): RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        if (layoutManager.findLastCompletelyVisibleItemPosition() >= layoutManager.itemCount - 6) {
            preloadCallback()
        }
    }
}