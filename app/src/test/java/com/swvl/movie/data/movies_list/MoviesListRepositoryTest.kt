package com.swvl.movie.data.movies_list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.swvl.movie.data.movies_list.local.MoviesListLocalDataSource
import com.swvl.movie.data.movies_list.model.MovieRaw
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Test

class MoviesListRepositoryTest {
    private val moviesListLocalDataSource: MoviesListLocalDataSource = mock()

    private val localDataSingleSubject = SingleSubject.create<MovieRaw>()

    private val movieRaw = MovieRaw(emptyList())

    private val repo = MoviesListRepository(moviesListLocalDataSource)

    @Before
    fun setUp() {
        whenever(moviesListLocalDataSource.getMoviesList()).thenReturn(localDataSingleSubject)
    }

    @Test
    fun `calling getMoviesList from repo should call getMoviesList from local data source`() {
        // act
        repo.getMoviesList()
        // verify
        verify(moviesListLocalDataSource).getMoviesList()
    }

    @Test
    fun `calling getMoviesList with green from local data source should return single with MovieRaw `() {
        localDataSingleSubject.onSuccess(movieRaw)

        repo
            .getMoviesList()
            .test()
            .assertValue(movieRaw)

    }

    @Test
    fun `calling getMoviesList with error from local data source should return error`() {
        val throwable = Throwable()

        localDataSingleSubject.onError(throwable)

        repo
            .getMoviesList()
            .test()
            .onError(throwable)
    }
}
