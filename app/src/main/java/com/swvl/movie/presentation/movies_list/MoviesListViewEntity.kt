package com.swvl.movie.presentation.movies_list

data class MoviesListItemVIewEntity(
    val title: String,
    val year: String,
    val cast: List<String>,
    val genres: List<String>
)
data class MoviesListViewEntity(
    val MoviesMap: Map<String,List<MoviesListItemVIewEntity>>
)
