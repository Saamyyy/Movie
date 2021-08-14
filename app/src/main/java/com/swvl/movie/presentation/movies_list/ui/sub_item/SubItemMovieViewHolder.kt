package com.swvl.movie.presentation.movies_list.ui.sub_item

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.swvl.movie.R
import com.swvl.movie.common.navigation.Navigator
import com.swvl.movie.common.navigation.NavigatorModel
import com.swvl.movie.presentation.movies_details.ui.*
import com.swvl.movie.presentation.movies_list.MoviesListItemVIewEntity


class SubItemMovieViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    View.inflate(parentView.context, R.layout.movie_sub_item, null)
) {
    private val container: ConstraintLayout by lazy { itemView.findViewById(R.id.movieItemContainer) }
    private val title: TextView by lazy { itemView.findViewById(R.id.movieTitle) }
    private val year: TextView by lazy { itemView.findViewById(R.id.movieYear) }

    fun bind(item: MoviesListItemVIewEntity) {
        title.text = item.title
        year.text = item.year

        container.setOnClickListener {
            val pBundle = Bundle()
            pBundle.putString(TITLE_VALUE_KEY, item.title)
            pBundle.putString(YEAR_VALUE_KEY, item.year)
            pBundle.putString(GENRES_VALUE_KEY, item.genres.toString())
            pBundle.putString(CAST_VALUE_KEY, item.cast.toString())
            Navigator.navigate(
                itemView.context, NavigatorModel(
                    action = MOVIE_DETAILS_ACTIVITY_ACTION,
                    options = pBundle
                )
            )
        }
    }
}
