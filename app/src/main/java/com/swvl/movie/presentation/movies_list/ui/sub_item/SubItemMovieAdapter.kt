package com.swvl.movie.presentation.movies_list.ui.sub_item

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swvl.movie.presentation.movies_list.MoviesListItemVIewEntity

class SubItemMovieAdapter(private val moviesList: List<MoviesListItemVIewEntity>) :
    RecyclerView.Adapter<SubItemMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SubItemMovieViewHolder(parent)

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: SubItemMovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }
}
