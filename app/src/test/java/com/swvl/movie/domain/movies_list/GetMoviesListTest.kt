package com.swvl.movie.domain.movies_list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.swvl.movie.data.movies_list.MoviesListRepository
import com.swvl.movie.data.movies_list.model.MovieRaw
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Test

class GetMoviesListTest {
    private val moviesListRepository: MoviesListRepository = mock()

    private val repoSingleSubject = SingleSubject.create<MovieRaw>()
    private val movieRaw = MovieRaw(emptyList())

    private val getMoviesList = GetMoviesList(moviesListRepository)

    @Before
    fun setUp() {
        whenever(moviesListRepository.getMoviesList()).thenReturn(repoSingleSubject)
    }

    @Test
    fun `calling getMoviesList from useCase should call getMoviesList from repo`() {
        getMoviesList.getMoviesList()

        verify(moviesListRepository).getMoviesList()
    }

    @Test
    fun `calling getMoviesList with green repo should return single with MovieRaw `() {
        repoSingleSubject.onSuccess(movieRaw)

        getMoviesList
            .getMoviesList()
            .test()
            .assertValue(movieRaw)
    }


    @Test
    fun `calling getMoviesList with error from  repo should return single with error `() {
        val throwable = Throwable()
        repoSingleSubject.onError(throwable)

        getMoviesList
            .getMoviesList()
            .test()
            .onError(throwable)
    }
}
