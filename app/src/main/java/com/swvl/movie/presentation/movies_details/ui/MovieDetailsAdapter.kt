package com.swvl.movie.presentation.movies_details.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class MovieDetailsAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<MovieDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieDetailsViewHolder(parent)

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: MovieDetailsViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }
}
