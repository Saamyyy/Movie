package com.swvl.movie.data.movie_details

import com.swvl.movie.data.movie_details.model.FlickrSearchResultRaw
import io.reactivex.Single

interface IMovieImagesRepository {
    fun getMovieImages(movieTittle:String): Single<FlickrSearchResultRaw>
}
