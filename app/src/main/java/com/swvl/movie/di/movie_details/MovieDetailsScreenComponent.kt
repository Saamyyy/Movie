package com.swvl.movie.di.movie_details

import com.swvl.movie.presentation.movies_details.ui.MovieDetailsActivity
import dagger.Subcomponent

@MovieDetails
@Subcomponent(modules = [MovieDetailsScreenDataModule::class])
interface MovieDetailsScreenComponent {
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}
