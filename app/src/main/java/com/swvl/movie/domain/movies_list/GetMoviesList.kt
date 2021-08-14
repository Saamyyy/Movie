package com.swvl.movie.domain.movies_list

import com.swvl.movie.data.movies_list.MoviesListRepository
import com.swvl.movie.presentation.movies_list.mapper.MoviesListViewEntityMapper
import javax.inject.Inject

class GetMoviesList @Inject constructor(
    private val moviesListRepository: MoviesListRepository
) {
    fun getMoviesList() =
        moviesListRepository
            .getMoviesList()
}
