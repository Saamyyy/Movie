package com.swvl.movie.presentation.movies_list.mapper

import com.swvl.movie.data.movies_list.model.MovieRaw
import com.swvl.movie.data.movies_list.model.MovieRawItem
import com.swvl.movie.presentation.movies_list.MoviesListItemVIewEntity
import com.swvl.movie.presentation.movies_list.MoviesListViewEntity
import io.reactivex.functions.Function
import javax.inject.Inject

class MoviesListViewEntityMapper @Inject constructor() : Function<MovieRaw, MoviesListViewEntity> {
    override fun apply(movieRaw: MovieRaw) =
        MoviesListViewEntity(mapTpGroupedMovies(movieRaw))

    private fun mapTpGroupedMovies(movieRaw: MovieRaw) =
        movieRaw
            .moviesList
            .map { mapToMoviesListItemVIewEntity(it) }
            .groupBy { it.year }

    private fun mapToMoviesListItemVIewEntity(it: MovieRawItem) =
        MoviesListItemVIewEntity(
            title = it.title,
            year = it.year,
            cast = it.cast,
            genres = it.genres,
        )
}
