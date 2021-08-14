package com.swvl.movie.presentation.movies_list.ui.section

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swvl.movie.R
import com.swvl.movie.presentation.movies_list.MoviesListItemVIewEntity
import com.swvl.movie.presentation.movies_list.ui.sub_item.SubItemMovieAdapter

class MovieSectionViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    View.inflate(parentView.context, R.layout.movie_section_item, null)
)  {
    private val year: TextView by lazy { itemView.findViewById(R.id.yearTextView) }
    private val moviesRecyclerView: RecyclerView by lazy { itemView.findViewById(R.id.moviesRecyclerView) }

    fun bind(releaseYear:String,moviesList:List<MoviesListItemVIewEntity>) {
        year.text = releaseYear
        moviesRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = SubItemMovieAdapter(moviesList)
        moviesRecyclerView.adapter = adapter
    }
}
