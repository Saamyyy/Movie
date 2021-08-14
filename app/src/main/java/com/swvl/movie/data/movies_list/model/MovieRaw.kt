package com.swvl.movie.data.movies_list.model

import com.google.gson.annotations.SerializedName

data class MovieRawItem(
    val title: String,
    val year: String,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
)

data class MovieRaw(
    @SerializedName("movies")
    val moviesList: List<MovieRawItem>
)
