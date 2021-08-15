package com.swvl.movie.presentation.movies_details.mapper

import com.swvl.movie.domain.movies_detilas.model.FlickrSearchResultDomainEntity
import com.swvl.movie.presentation.movies_details.FlickrSearchResultViewEntity
import org.junit.Assert
import org.junit.Test

class FlickrSearchResultViewEntityMapperTest {
    private val flickrSearchResultDomainEntity = FlickrSearchResultDomainEntity(
        listOf(
            "",
            "link1",
            "link2"
        )
    )
    private val expected = FlickrSearchResultViewEntity(
        listOf(
            "link1",
            "link2"
        )
    )

    private val mapper = FlickrSearchResultViewEntityMapper()

    @Test
    fun `mapper should remove empty strings from list and return valid FlickrSearchResultViewEntity`() {
        val actual = mapper.apply(flickrSearchResultDomainEntity)

        Assert.assertEquals(expected, actual)

    }
}
