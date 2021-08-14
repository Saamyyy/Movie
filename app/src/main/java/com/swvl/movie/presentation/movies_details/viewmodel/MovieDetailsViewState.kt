package com.swvl.movie.presentation.movies_details.viewmodel

import com.swvl.movie.presentation.movies_details.FlickrSearchResultViewEntity

sealed class MovieDetailsViewState{
    object Loading: MovieDetailsViewState()
    object Empty: MovieDetailsViewState()
    data class Error(val message:String): MovieDetailsViewState()
    data class Content(val flickrSearchResultViewEntity: FlickrSearchResultViewEntity): MovieDetailsViewState()
}
