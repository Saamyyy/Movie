package com.swvl.movie.di

import com.adyen.android.assignment.di.ApplicationScope
import com.swvl.movie.di.movie_details.MovieDetailsScreenComponent
import com.swvl.movie.di.movie_details.MovieDetailsScreenDataModule
import com.swvl.movie.di.movies_list.MoviesListComponent
import com.swvl.movie.di.movies_list.MoviesListModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun moviesListScreenComponent(moviesListModule: MoviesListModule): MoviesListComponent
    fun movieDetailScreenComponent(moviesListModule: MovieDetailsScreenDataModule): MovieDetailsScreenComponent

}
