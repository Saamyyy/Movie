package com.swvl.movie.presentation.movies_details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.swvl.movie.common.error_mapper.AppErrorMapper
import com.swvl.movie.common.error_mapper.AppErrorModel
import com.swvl.movie.domain.movies_detilas.FetchMovieImages
import com.swvl.movie.domain.movies_detilas.model.FlickrSearchResultDomainEntity
import com.swvl.movie.presentation.movies_details.FlickrSearchResultViewEntity
import com.swvl.movie.presentation.movies_details.mapper.FlickrSearchResultViewEntityMapper
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.SingleSubject
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieDetailsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val fetchMovieImages: FetchMovieImages = mock()
    private val flickrSearchResultViewEntityMapper: FlickrSearchResultViewEntityMapper = mock()
    private val appErrorMapper: AppErrorMapper = mock()
    private val io: TestScheduler = TestScheduler()
    private val main: TestScheduler = TestScheduler()

    private val viewModel = MovieDetailsViewModel(
        fetchMovieImages,
        flickrSearchResultViewEntityMapper,
        appErrorMapper,
        io,
        main
    )
    private val fetchMovieImagesSingleSubject =
        SingleSubject.create<FlickrSearchResultDomainEntity>()
    private val flickrSearchResultDomainEntity = FlickrSearchResultDomainEntity(listOf())
    private val flickrSearchResultViewEntity = FlickrSearchResultViewEntity(listOf("link"))

    @Before
    fun setUp() {
        whenever(fetchMovieImages.getMovieImageUrls(any())).thenReturn(fetchMovieImagesSingleSubject)
    }

    @Test
    fun `calling getMovieImages should emits loading first `() {
        // arrange
        fetchMovieImagesSingleSubject.onSuccess(flickrSearchResultDomainEntity)
        whenever(flickrSearchResultViewEntityMapper.apply(any())).thenReturn(
            flickrSearchResultViewEntity
        )
        viewModel.onCreate()
        // act
        viewModel.getMovieImages("search")
        // assert
        Assert.assertEquals(
            MovieDetailsViewState.Loading,
            viewModel.movieDetailsVIewState.value
        )
    }

    @Test
    fun `calling getMovieImages should emits content if usecase and mapper was green`() {
        // arrange
        fetchMovieImagesSingleSubject.onSuccess(flickrSearchResultDomainEntity)
        whenever(flickrSearchResultViewEntityMapper.apply(any())).thenReturn(
            flickrSearchResultViewEntity
        )
        viewModel.onCreate()
        // act
        viewModel.getMovieImages("search")
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MovieDetailsViewState.Content(flickrSearchResultViewEntity),
            viewModel.movieDetailsVIewState.value
        )
    }

    @Test
    fun `calling getMovieImages should emits empty if mapper return empty list`() {
        // arrange
        fetchMovieImagesSingleSubject.onSuccess(flickrSearchResultDomainEntity)
        whenever(flickrSearchResultViewEntityMapper.apply(any())).thenReturn(
            FlickrSearchResultViewEntity(listOf())
        )
        viewModel.onCreate()
        // act
        viewModel.getMovieImages("search")
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MovieDetailsViewState.Empty,
            viewModel.movieDetailsVIewState.value
        )
    }

    @Test
    fun `calling getMovieImages should emits error if usecase emits error`() {
        // arrange
        val throwable = Throwable("error")

        fetchMovieImagesSingleSubject.onError(throwable)
        whenever(flickrSearchResultViewEntityMapper.apply(any())).thenReturn(
            FlickrSearchResultViewEntity(listOf())
        )
        whenever(appErrorMapper.map(any())).thenReturn(AppErrorModel("", "error"))

        viewModel.onCreate()
        // act
        viewModel.getMovieImages("search")
        io.triggerActions()
        main.triggerActions()
        // assert
        Assert.assertEquals(
            MovieDetailsViewState.Error("error"),
            viewModel.movieDetailsVIewState.value
        )
    }
}
