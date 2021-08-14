package com.swvl.movie.di.movie_details

import androidx.lifecycle.ViewModel
import com.adyen.android.assignment.di.BaseViewModelModule
import com.swvl.movie.base.viewmodel.ViewModelKey
import com.swvl.movie.common.error_mapper.AppErrorMapper
import com.swvl.movie.data.movie_details.MovieImagesRepository
import com.swvl.movie.data.movie_details.api.FlickrApiService
import com.swvl.movie.domain.movies_detilas.FetchMovieImages
import com.swvl.movie.presentation.movies_details.mapper.FlickrSearchResultViewEntityMapper
import com.swvl.movie.presentation.movies_details.viewmodel.MovieDetailsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit


@Module(includes = [BaseViewModelModule::class])

class MovieDetailsScreenDataModule {

    @Provides
    @MovieDetails
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    fun bindMainViewModel(
        fetchMovieImages: FetchMovieImages,
        flickrSearchResultViewEntityMapper: FlickrSearchResultViewEntityMapper,
        appErrorMapper: AppErrorMapper,
    ): ViewModel {
        return MovieDetailsViewModel(
            fetchMovieImages,
            flickrSearchResultViewEntityMapper,
            appErrorMapper,
            Schedulers.computation(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    @MovieDetails
    fun provideMovieImagesRepository(
        flickrApiService: FlickrApiService,
    ) = MovieImagesRepository(flickrApiService)

    @Provides
    @MovieDetails
    fun providePlacesService(retrofit: Retrofit): FlickrApiService {
        return retrofit.create<FlickrApiService>(FlickrApiService::class.java)
    }
}
