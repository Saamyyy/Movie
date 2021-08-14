package com.swvl.movie.presentation.movies_details.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swvl.movie.R

class MovieDetailsViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(
    View.inflate(parentView.context, R.layout.movie_details_list_iten, null)
) {
    private val progress by lazy { itemView.findViewById<View>(R.id.movieDetailsProgress) }
    private val image: ImageView by lazy { itemView.findViewById(R.id.movieDetailsImage) }

    fun bind(url: String) {
        image.visibility = View.VISIBLE
        Glide.with(image.context)
            .load(url)
            .placeholder(android.R.drawable.ic_menu_crop)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .error(android.R.drawable.ic_dialog_alert)
            .into(image)
        progress.visibility = View.GONE

    }

}
