package com.adyen.android.assignment.di

import com.swvl.movie.di.AppModule
import com.swvl.movie.di.movie_details.RecommendedVenuesScreenComponent
import com.swvl.movie.di.movie_details.RecommendedVenuesScreenDataModule
import com.swvl.movie.di.movies_list.MoviesListComponent
import com.swvl.movie.di.movies_list.MoviesListModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun moviesListScreenComponent(moviesListModule: MoviesListModule) : MoviesListComponent
}
