package com.swvl.movie.di.movie_details

import com.adyen.android.assignment.di.BaseViewModelModule
import com.adyen.android.assignment.di.test.MoviesList
import com.swvl.movie.data.movie_details.MovieImagesRepository
import com.swvl.movie.data.movie_details.api.FlickrApiService
import com.swvl.movie.data.movies_list.MoviesListRepository
import com.swvl.movie.data.movies_list.local.MoviesListLocalDataSource
import dagger.Module
import dagger.Provides


@Module(includes = [BaseViewModelModule::class])

class MovieDetailsScreenDataModule {
    @Provides
    @MovieDetails
    fun provideMovieImagesRepository(
        flickrApiService: FlickrApiService,
    ) = MovieImagesRepository(flickrApiService)
}
