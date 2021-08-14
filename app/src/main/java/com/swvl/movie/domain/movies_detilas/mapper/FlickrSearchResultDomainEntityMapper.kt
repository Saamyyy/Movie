package com.swvl.movie.domain.movies_detilas.mapper

import com.swvl.movie.data.movie_details.model.FlickrPicture
import com.swvl.movie.data.movie_details.model.FlickrSearchResultRaw
import com.swvl.movie.domain.movies_detilas.model.FlickrSearchResultDomainEntity
import io.reactivex.functions.Function
import javax.inject.Inject

class FlickrSearchResultDomainEntityMapper @Inject constructor() :
    Function<FlickrSearchResultRaw, FlickrSearchResultDomainEntity> {
    override fun apply(raw: FlickrSearchResultRaw): FlickrSearchResultDomainEntity {
        return FlickrSearchResultDomainEntity(getImageUrlsList(raw))
    }

    private fun getImageUrlsList(raw: FlickrSearchResultRaw): MutableList<String> {
        val imageUrls = mutableListOf<String>()
        raw.metadata
            ?.photos
            ?.forEach { imageUrls.add(buildImageUrl(it)) }
        return imageUrls
    }

    private fun buildImageUrl(flickrPicture: FlickrPicture) =
        "http://farm${flickrPicture.farm}.static.flickr.com/${flickrPicture.server}/${flickrPicture.id}_${flickrPicture.secret}.jpg"
}
