package com.swvl.movie.presentation.movies_list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.swvl.movie.common.error_mapper.AppErrorMapper
import com.swvl.movie.common.error_mapper.AppErrorModel
import com.swvl.movie.data.movies_list.model.MovieRaw
import com.swvl.movie.data.movies_list.model.MovieRawItem
import com.swvl.movie.domain.movies_list.GetFilteredMovesList
import com.swvl.movie.domain.movies_list.GetMoviesList
import com.swvl.movie.presentation.movies_list.MoviesListItemVIewEntity
import com.swvl.movie.presentation.movies_list.MoviesListViewEntity
import com.swvl.movie.presentation.movies_list.mapper.MoviesListViewEntityMapper
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.SingleSubject
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MoviesListViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getMoviesList: GetMoviesList = mock()
    private val getFilteredMovesList: GetFilteredMovesList = mock()
    private val moviesListViewEntityMapper: MoviesListViewEntityMapper = mock()
    private val appErrorMapper: AppErrorMapper = mock()
    private val io: TestScheduler = TestScheduler()
    private val main: TestScheduler = TestScheduler()

    private val viewModel = MoviesListViewModel(
        getMoviesList,
        getFilteredMovesList,
        moviesListViewEntityMapper,
        appErrorMapper,
        io,
        main
    )

    private val getMoviesListSingleSubject = SingleSubject.create<MovieRaw>()
    private val getFilteredMovesListSingleSubject = SingleSubject.create<MovieRaw>()

    private val movieRawItem1 = MovieRawItem("a", "2009", emptyList(), emptyList(), 10)
    private val movieRawItem2 = MovieRawItem("a", "2009", emptyList(), emptyList(), 9)
    private val movieRawItem3 = MovieRawItem("a", "2009", emptyList(), emptyList(), 8)
    private val movieRaw = MovieRaw(
        listOf(
            movieRawItem1,
            movieRawItem2,
            movieRawItem3
        )
    )
    private val moviesListItemVIewEntity = listOf(
        MoviesListItemVIewEntity("a", "2009", emptyList(), emptyList()),
        MoviesListItemVIewEntity("a", "2009", emptyList(), emptyList()),
        MoviesListItemVIewEntity("a", "2009", emptyList(), emptyList()),
    )

    private val moviesListViewEntity =
        MoviesListViewEntity(mapOf("2009" to moviesListItemVIewEntity))

    @Before
    fun setUp() {
        whenever(getMoviesList.getMoviesList()).thenReturn(getMoviesListSingleSubject)
        whenever(getFilteredMovesList.getFilteredMovesList(any())).thenReturn(
            getFilteredMovesListSingleSubject
        )
        whenever(appErrorMapper.map(any())).thenReturn(AppErrorModel("", "error"))
    }

    @Test
    fun `calling onBind should emits loading first `() {
        // arrange
        getMoviesListSingleSubject.onSuccess(movieRaw)
        viewModel.onCreate()
        // act
        viewModel.onBind()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Loading,
            viewModel.moviesListViewState.value
        )
    }

    @Test
    fun `calling onBind should emits Content if usecase return data and mapping succeeded`() {
        // arrange
        getMoviesListSingleSubject.onSuccess(movieRaw)
        whenever(moviesListViewEntityMapper.apply(any())).thenReturn(moviesListViewEntity)
        viewModel.onCreate()
        // act
        viewModel.onBind()
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Content(moviesListViewEntity),
            viewModel.moviesListViewState.value
        )
    }

    @Test
    fun `calling onBind should emits Empty if useCase return  and mapping return empty data `() {
        // arrange
        getMoviesListSingleSubject.onSuccess(movieRaw)
        whenever(moviesListViewEntityMapper.apply(any())).thenReturn(MoviesListViewEntity(mapOf()))
        viewModel.onCreate()
        // act
        viewModel.onBind()
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Empty,
            viewModel.moviesListViewState.value
        )
    }
    @Test
    fun `calling onBind should emits Error if useCase errors`() {
        // arrange
        val throwable = Throwable("error")

        getMoviesListSingleSubject.onError(throwable)
        viewModel.onCreate()
        // act
        viewModel.onBind()
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Error("error"),
            viewModel.moviesListViewState.value
        )
    }

    @Test
    fun `calling applySearchTerm with empty string should emits Content  from getMoviesList usecase `() {
        // arrange
        getMoviesListSingleSubject.onSuccess(movieRaw)
        whenever(moviesListViewEntityMapper.apply(any())).thenReturn(moviesListViewEntity)

        viewModel.onCreate()
        // act
        viewModel.applySearchTerm("")
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Content(moviesListViewEntity),
            viewModel.moviesListViewState.value
        )
    }

    @Test
    fun `calling applySearchTerm with search term should emits Loading first `() {
        // arrange
        getFilteredMovesListSingleSubject.onSuccess(movieRaw)
        whenever(moviesListViewEntityMapper.apply(any())).thenReturn(moviesListViewEntity)

        viewModel.onCreate()
        // act
        viewModel.applySearchTerm("a")
        // assert
        Assert.assertEquals(
            MoviesListViewState.Loading,
            viewModel.moviesListViewState.value
        )
    }

    @Test
    fun `calling applySearchTerm with search term  should emits Content from getFilteredMovesListS usecase `() {
        // arrange
        getFilteredMovesListSingleSubject.onSuccess(movieRaw)
        whenever(moviesListViewEntityMapper.apply(any())).thenReturn(moviesListViewEntity)

        viewModel.onCreate()
        // act
        viewModel.applySearchTerm("a")
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Content(moviesListViewEntity),
            viewModel.moviesListViewState.value
        )
    }
    @Test
    fun `calling applySearchTerm with search term should emits empty if no movies match search terma  `() {
        // arrange
        getFilteredMovesListSingleSubject.onSuccess(movieRaw)
        whenever(moviesListViewEntityMapper.apply(any())).thenReturn(MoviesListViewEntity(mapOf()))

        viewModel.onCreate()
        // act
        viewModel.applySearchTerm("a")
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Empty,
            viewModel.moviesListViewState.value
        )
    }

    @Test
    fun `calling applySearchTerm with search term should emits Error if useCase errors`() {
        // arrange
        val throwable = Throwable("error")
        getFilteredMovesListSingleSubject.onError(throwable)
        whenever(moviesListViewEntityMapper.apply(any())).thenReturn(MoviesListViewEntity(mapOf()))

        viewModel.onCreate()
        // act
        viewModel.applySearchTerm("a")
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MoviesListViewState.Error("error"),
            viewModel.moviesListViewState.value
        )
    }
}
