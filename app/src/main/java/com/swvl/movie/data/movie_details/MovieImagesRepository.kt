package com.swvl.movie.data.movie_details

import com.swvl.movie.data.movie_details.api.FlickrApiService
import com.swvl.movie.data.movie_details.model.FlickrSearchResultRaw
import io.reactivex.Single
import javax.inject.Inject

class MovieImagesRepository @Inject constructor(
    private val flickrApiService: FlickrApiService
) : IMovieImagesRepository {
    override fun getMovieImages(movieTittle: String): Single<FlickrSearchResultRaw> {
        return flickrApiService.requestPictures(title = movieTittle)
    }
}
