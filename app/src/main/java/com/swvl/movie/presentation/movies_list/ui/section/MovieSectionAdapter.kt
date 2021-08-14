package com.swvl.movie.presentation.movies_list.ui.section

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swvl.movie.presentation.movies_list.MoviesListViewEntity

class MovieSectionAdapter(private val moviesListViewEntity: MoviesListViewEntity) :
    RecyclerView.Adapter<MovieSectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieSectionViewHolder(parent)

    override fun getItemCount(): Int {
        return moviesListViewEntity.MoviesMap.keys.size
    }

    override fun onBindViewHolder(holder: MovieSectionViewHolder, position: Int) {
        val key = moviesListViewEntity.MoviesMap.keys.toTypedArray()[position]
        holder.bind(key, moviesListViewEntity.MoviesMap[key] ?: emptyList())
    }
}
