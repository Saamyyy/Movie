package com.swvl.movie.di.movie_details

import dagger.Subcomponent

@MovieDetails
@Subcomponent(modules = [MovieDetailsScreenDataModule::class])
interface MovieDetailsScreenComponent{
}
