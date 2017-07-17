package com.siegmund.moviesapp.ui.main

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
import com.siegmund.moviesapp.R
import com.siegmund.moviesapp.api.Movie
import com.siegmund.moviesapp.ui.util.asGermanDate
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale.GERMAN

class MoviesAdapter(val listener: (Movie, Int) -> Unit): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var movies: MutableList<Movie> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun addItems(new: List<Movie>) {
        this.movies.addAll(movies.size, new)
        notifyItemRangeInserted(this.movies.size, new.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card_view, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies[position].let {
            holder.configure(url = it.posterPath)
        }
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.image) lateinit var imageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
            this.itemView.setOnClickListener { listener(movies[adapterPosition], adapterPosition) }
        }

        fun configure(url: String?) {
            val uri = Uri.parse("http://image.tmdb.org/t/p/original/$url")
            Glide.with(itemView.context).load(uri).into(imageView)
        }
    }
}