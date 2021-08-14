package com.swvl.movie.data.movies_list

import com.swvl.movie.data.movies_list.model.MovieRaw
import io.reactivex.Single

interface IMoviesListRepository {
    fun getMoviesList() : Single<MovieRaw>
}
