package com.swvl.movie.presentation.movies_list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.swvl.movie.base.viewmodel.BaseViewModel
import com.swvl.movie.common.error_mapper.AppErrorMapper
import com.swvl.movie.domain.movies_list.GetFilteredMovesList
import com.swvl.movie.domain.movies_list.GetMoviesList
import com.swvl.movie.presentation.movies_list.MoviesListViewEntity
import com.swvl.movie.presentation.movies_list.mapper.MoviesListViewEntityMapper
import io.reactivex.Scheduler
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val getMoviesList: GetMoviesList,
    private val getFilteredMovesList: GetFilteredMovesList,
    private val moviesListViewEntityMapper: MoviesListViewEntityMapper,
    private val appErrorMapper: AppErrorMapper,
    private val io: Scheduler,
    private val main: Scheduler
) : BaseViewModel() {
    val moviesListViewState = MutableLiveData<MoviesListViewState>()
    override fun onBind() {
        getMoviesList()
    }


    fun applySearchTerm(term: String) {
        if (term.isNullOrEmpty()) {
            getMoviesList()
        } else {
            getMoviesWithSearchTerm(term)
        }
    }


    private fun getMoviesList() {
        getMoviesList
            .getMoviesList()
            .map(moviesListViewEntityMapper)
            .subscribeOn(io)
            .observeOn(main)
            .doOnSubscribe { moviesListViewState.value = MoviesListViewState.Loading }
            .subscribe(::handleOnFetchMoviesSuccess, ::handleOnFetchMoviesError)
            .addToCompositeDisposable()
    }

    private fun getMoviesWithSearchTerm(term: String) {
        getFilteredMovesList
            .getFilteredMovesList(term)
            .map(moviesListViewEntityMapper)
            .subscribeOn(io)
            .observeOn(main)
            .doOnSubscribe { moviesListViewState.value = MoviesListViewState.Loading }
            .subscribe(::handleOnFetchMoviesSuccess, ::handleOnFetchMoviesError)
            .addToCompositeDisposable()
    }

    private fun handleOnFetchMoviesError(it: Throwable) {
        moviesListViewState.value =
            MoviesListViewState.Error(appErrorMapper.map(it).errorMessage)
    }

    private fun handleOnFetchMoviesSuccess(it: MoviesListViewEntity) {
        if (it.MoviesMap.isNotEmpty()) {
            moviesListViewState.value = MoviesListViewState.Content(it)
        } else {
            moviesListViewState.value = MoviesListViewState.Empty
        }
    }
}
