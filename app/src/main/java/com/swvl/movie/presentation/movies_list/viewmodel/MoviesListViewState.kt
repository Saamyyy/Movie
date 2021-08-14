package com.swvl.movie.presentation.movies_list.viewmodel

import com.swvl.movie.presentation.movies_list.MoviesListViewEntity

sealed class MoviesListViewState{
    object Loading: MoviesListViewState()
    object Empty: MoviesListViewState()
    data class Error(val message:String): MoviesListViewState()
    data class Content(val moviesListViewState: MoviesListViewEntity):MoviesListViewState()
}
