package com.swvl.movie.presentation.movies_list.ui.sub_item

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swvl.movie.R
import com.swvl.movie.presentation.movies_list.MoviesListItemVIewEntity

class SubItemMovieViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    View.inflate(parentView.context, R.layout.movie_sub_item, null)
) {
    private val title: TextView by lazy { itemView.findViewById(R.id.movieTitle) }
    private val year: TextView by lazy { itemView.findViewById(R.id.movieYear) }

    fun bind(item: MoviesListItemVIewEntity) {
        title.text = item.title
        year.text = item.year
    }
}
