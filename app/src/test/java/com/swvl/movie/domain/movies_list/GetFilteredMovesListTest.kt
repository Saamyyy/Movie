package com.swvl.movie.domain.movies_list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.swvl.movie.data.movies_list.MoviesListRepository
import com.swvl.movie.data.movies_list.model.MovieRaw
import com.swvl.movie.data.movies_list.model.MovieRawItem
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Test

class GetFilteredMovesListTest {
    private val moviesListRepository: MoviesListRepository = mock()

    private val movieRawItem1 = MovieRawItem("a", "2009", emptyList(), emptyList(), 10)
    private val movieRawItem2 = MovieRawItem("a", "2010", emptyList(), emptyList(), 9)
    private val movieRawItem3 = MovieRawItem("a", "2011", emptyList(), emptyList(), 8)
    private val movieRawItem4 = MovieRawItem("a", "2012", emptyList(), emptyList(), 7)
    private val movieRawItem5 = MovieRawItem("a", "2013", emptyList(), emptyList(), 6)
    private val movieRawItem6 = MovieRawItem("b", "2014", emptyList(), emptyList(), 12)

    private val movieRaw = MovieRaw(
        listOf(
            movieRawItem2,
            movieRawItem1,
            movieRawItem3,
            movieRawItem5,
            movieRawItem6,
            movieRawItem4,
        )
    )

    private val sortedMovieRaw = MovieRaw(
        listOf(
            movieRawItem1,
            movieRawItem2,
            movieRawItem3,
            movieRawItem4,
            movieRawItem5,
        )
    )

    private val repoSingleSubject = SingleSubject.create<MovieRaw>()
    private val getFilteredMovesList= GetFilteredMovesList(moviesListRepository)

    @Before
    fun setUp(){
        whenever(moviesListRepository.getMoviesList()).thenReturn(repoSingleSubject)
    }

    @Test
    fun `calling getFilteredMovesList should return top five sorted movies that contains term`(){
        repoSingleSubject.onSuccess(movieRaw)

        getFilteredMovesList
            .getFilteredMovesList("a")
            .test()
            .assertValue(sortedMovieRaw)
    }

    @Test
    fun `calling getFilteredMovesList should return single with error if repo errors`(){
        val throwable = Throwable()

        repoSingleSubject.onError(throwable)

        getFilteredMovesList
            .getFilteredMovesList("a")
            .test()
            .onError(throwable)
    }


}
