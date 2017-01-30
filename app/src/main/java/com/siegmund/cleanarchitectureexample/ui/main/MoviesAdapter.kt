package com.siegmund.cleanarchitectureexample.ui.main

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import com.siegmund.cleanarchitectureexample.R
import com.siegmund.cleanarchitectureexample.api.Movie

class MoviesAdapter(val listener: (Movie) -> Unit): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var movies: List<Movie> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card_view, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies[position].let {
            holder.configure(
                    url = it.posterPath,
                    title = it.title,
                    subtitle = it.releaseDate
            )
        }
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.image) lateinit var imageView: ImageView
        @BindView(R.id.title) lateinit var titleView: TextView
        @BindView(R.id.subtitle) lateinit var subtitleView: TextView

        init {
            ButterKnife.bind(this, itemView)
            this.itemView.setOnClickListener { listener(movies[adapterPosition]) }
        }

        fun configure(url: String?, title: String, subtitle: String) {
            val uri = Uri.parse(url)
            Glide.with(itemView.context).load(uri).into(imageView)
            titleView.text = title
            subtitleView.text = subtitle
        }
    }
}