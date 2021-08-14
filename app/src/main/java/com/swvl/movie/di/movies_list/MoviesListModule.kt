package com.swvl.movie.di.movies_list

import androidx.lifecycle.ViewModel
import com.adyen.android.assignment.di.BaseViewModelModule
import com.adyen.android.assignment.di.test.MoviesList
import com.swvl.movie.base.viewmodel.ViewModelKey
import com.swvl.movie.common.error_mapper.AppErrorMapper
import com.swvl.movie.data.movies_list.MoviesListRepository
import com.swvl.movie.data.movies_list.local.MoviesListLocalDataSource
import com.swvl.movie.domain.movies_list.GetFilteredMovesList
import com.swvl.movie.domain.movies_list.GetMoviesList
import com.swvl.movie.presentation.movies_list.mapper.MoviesListViewEntityMapper
import com.swvl.movie.presentation.movies_list.viewmodel.MoviesListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module(includes = [BaseViewModelModule::class])

class MoviesListModule {
    @Provides
    @MoviesList
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    fun bindMainViewModel(
        getMoviesList: GetMoviesList,
        moviesListViewEntityMapper: MoviesListViewEntityMapper,
        getFilteredMovesList: GetFilteredMovesList,
        appErrorMapper: AppErrorMapper,
    ): ViewModel {
        return MoviesListViewModel(
            getMoviesList,
            getFilteredMovesList,
            moviesListViewEntityMapper,
            appErrorMapper,
            Schedulers.computation(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    @MoviesList
    fun provideMoviesListRepository(
        moviesListLocalDataSource: MoviesListLocalDataSource,
    ) = MoviesListRepository(moviesListLocalDataSource)
}
