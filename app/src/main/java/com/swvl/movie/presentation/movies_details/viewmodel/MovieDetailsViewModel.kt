package com.swvl.movie.presentation.movies_details.viewmodel

import androidx.lifecycle.MutableLiveData
import com.swvl.movie.base.viewmodel.BaseViewModel
import com.swvl.movie.common.error_mapper.AppErrorMapper
import com.swvl.movie.domain.movies_detilas.FetchMovieImages
import com.swvl.movie.presentation.movies_details.FlickrSearchResultViewEntity
import com.swvl.movie.presentation.movies_details.mapper.FlickrSearchResultViewEntityMapper
import io.reactivex.Scheduler
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val fetchMovieImages: FetchMovieImages,
    private val flickrSearchResultViewEntityMapper: FlickrSearchResultViewEntityMapper,
    private val appErrorMapper: AppErrorMapper,
    private val io: Scheduler,
    private val main: Scheduler
) : BaseViewModel() {
    val movieDetailsVIewState = MutableLiveData<MovieDetailsViewState>()
    override fun onBind() {}
    fun getMovieImages(movieName: String) {
        fetchMovieImages
            .getMovieImageUrls(movieName)
            .map(flickrSearchResultViewEntityMapper)
            .subscribeOn(io)
            .observeOn(main)
            .doOnSubscribe { movieDetailsVIewState.value = MovieDetailsViewState.Loading }
            .subscribe(::handleOnFetchMovieImagesSuccess, ::handleOnFetchMovieImagesError)
            .addToCompositeDisposable()
    }

    private fun handleOnFetchMovieImagesSuccess(it: FlickrSearchResultViewEntity) {
        if (it.movieUrls.isNotEmpty()) {
            movieDetailsVIewState.value = MovieDetailsViewState.Content(it)
        } else {
            movieDetailsVIewState.value = MovieDetailsViewState.Empty
        }
    }

    private fun handleOnFetchMovieImagesError(it: Throwable) {
        movieDetailsVIewState.value =
            MovieDetailsViewState.Error(appErrorMapper.map(it).errorMessage)
    }

}
