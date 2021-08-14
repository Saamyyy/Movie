package com.swvl.movie.presentation.movies_details.mapper

import com.swvl.movie.domain.movies_detilas.model.FlickrSearchResultDomainEntity
import com.swvl.movie.presentation.movies_details.FlickrSearchResultViewEntity
import io.reactivex.functions.Function
import javax.inject.Inject

class FlickrSearchResultViewEntityMapper @Inject constructor() :
    Function<FlickrSearchResultDomainEntity, FlickrSearchResultViewEntity> {
    override fun apply(domainEntity: FlickrSearchResultDomainEntity): FlickrSearchResultViewEntity {
        val moviesUrl = mutableListOf<String>()
        domainEntity.movieUrls
            .map {
                if (it.isNotEmpty()) {
                    moviesUrl.add(it)
                }
            }
        return FlickrSearchResultViewEntity(moviesUrl)
    }
}
