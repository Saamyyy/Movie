package com.swvl.movie.data.movie_details.api

import com.swvl.movie.BuildConfig
import com.swvl.movie.data.movie_details.model.FlickrSearchResultRaw
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    fun requestPictures(
        @Query("api_key") apiKey: String= BuildConfig.API_KEY,
        @Query("text") title: String,
        @Query("page") page: Int?= 1,
        @Query("per_page") itemsPerPage: Int?= 20
    ): Single<FlickrSearchResultRaw>
}
