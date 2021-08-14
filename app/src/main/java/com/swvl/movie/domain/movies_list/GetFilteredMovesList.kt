package com.swvl.movie.domain.movies_list

import com.swvl.movie.data.movies_list.MoviesListRepository
import com.swvl.movie.data.movies_list.model.MovieRaw
import io.reactivex.Single
import javax.inject.Inject

class GetFilteredMovesList @Inject constructor(
    private val moviesListRepository: MoviesListRepository
) {
    fun getFilteredMovesList(searchTerm: String): Single<MovieRaw> {
        return moviesListRepository
            .getMoviesList()
            .flatMap { Single.just(applySearchTerm(it, searchTerm)) }
            .flatMap { Single.just(getTopFiveWithRating(it)) }
            .flatMap { Single.just(sortItemsByYear(it)) }
    }

    private fun applySearchTerm(movieRaw: MovieRaw, searchTerm: String) =
        MovieRaw(movieRaw.moviesList.filter {
            it.title.lowercase().contains(searchTerm.lowercase())
        })

    private fun getTopFiveWithRating(movieRaw: MovieRaw) =
        MovieRaw(movieRaw.moviesList
            .sortedByDescending { it.rating }
            .take(5)
        )

    private fun sortItemsByYear(movieRaw: MovieRaw) =
        MovieRaw(movieRaw.moviesList.sortedBy { it.year.toInt() })
}
