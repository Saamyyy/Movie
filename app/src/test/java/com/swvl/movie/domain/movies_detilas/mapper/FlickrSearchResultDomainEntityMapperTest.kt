package com.swvl.movie.domain.movies_detilas.mapper

import com.swvl.movie.data.movie_details.model.FlickrPicture
import com.swvl.movie.data.movie_details.model.FlickrPicturesMetadata
import com.swvl.movie.data.movie_details.model.FlickrSearchResultRaw
import com.swvl.movie.domain.movies_detilas.model.FlickrSearchResultDomainEntity
import org.junit.Assert
import org.junit.Test

class FlickrSearchResultDomainEntityMapperTest {
    private val expectedPictures = listOf(
        FlickrPicture(farm = "1A", server = "1B", id = "1C", secret = "1D"),
        FlickrPicture(farm = "2A", server = "2B", id = "2C", secret = "2D"),
        FlickrPicture(farm = "3A", server = "3B", id = "3C", secret = "3D")
    )
    private val flickrPicturesMetadata = FlickrPicturesMetadata(expectedPictures)
    private val flickrSearchResultRaw = FlickrSearchResultRaw(flickrPicturesMetadata)
    private val expected = FlickrSearchResultDomainEntity(
        listOf(
            "http://farm1A.static.flickr.com/1B/1C_1D.jpg",
            "http://farm2A.static.flickr.com/2B/2C_2D.jpg",
            "http://farm3A.static.flickr.com/3B/3C_3D.jpg"
        )
    )

    private val mapper = FlickrSearchResultDomainEntityMapper()

    @Test
    fun `mapper should map to view entity correctly`() {
        val actual = mapper.apply(flickrSearchResultRaw)

        Assert.assertEquals(expected, actual)
    }
}
