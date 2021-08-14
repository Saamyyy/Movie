package com.swvl.movie.di.movies_list

import com.adyen.android.assignment.di.test.MoviesList
import com.swvl.movie.presentation.movies_list.ui.MoviesListActivity
import dagger.Subcomponent


@MoviesList
@Subcomponent(modules = [MoviesListModule::class])
interface MoviesListComponent {
    fun inject(mainActivity: MoviesListActivity)
}
