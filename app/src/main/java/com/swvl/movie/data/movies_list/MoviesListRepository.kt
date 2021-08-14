package com.swvl.movie.data.movies_list

import com.swvl.movie.data.movies_list.local.MoviesListLocalDataSource
import javax.inject.Inject

class MoviesListRepository @Inject constructor(
    private val moviesListLocalDataSource: MoviesListLocalDataSource
) : IMoviesListRepository {
    override fun getMoviesList() = moviesListLocalDataSource.getMoviesList()
}
