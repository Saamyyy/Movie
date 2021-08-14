package com.swvl.movie.domain.movies_detilas

import com.swvl.movie.data.movie_details.MovieImagesRepository
import com.swvl.movie.domain.movies_detilas.mapper.FlickrSearchResultDomainEntityMapper
import javax.inject.Inject

class FetchMovieImages @Inject constructor(
    private val flickrSearchResultDomainEntityMapper: FlickrSearchResultDomainEntityMapper,
    private val movieImagesRepository: MovieImagesRepository
) {
    fun getMovieImageUrls(movieName: String) =
        movieImagesRepository
            .getMovieImages(movieName)
            .map(flickrSearchResultDomainEntityMapper)
}
