package com.swvl.movie.data.movie_details

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.swvl.movie.data.movie_details.api.FlickrApiService
import com.swvl.movie.data.movie_details.model.FlickrSearchResultRaw
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Test

class MovieImagesRepositoryTest {
    private val flickrApiService: FlickrApiService = mock()
    private val flickrApiServiceSingleSubject = SingleSubject.create<FlickrSearchResultRaw>()

    private val repo = MovieImagesRepository(flickrApiService)

    @Before
    fun setUp() {
        whenever(
            flickrApiService.requestPictures(
                any(),
                any(),
                any(),
                any()
            )
        ).thenReturn(flickrApiServiceSingleSubject)
    }

    @Test
    fun `calling getMovieImages from repo with green service should return single of FlickrSearchResultRaw `() {
        val flickrSearchResultRaw = FlickrSearchResultRaw()
        flickrApiServiceSingleSubject.onSuccess(flickrSearchResultRaw)

        repo.getMovieImages("")
            .test()
            .assertValue(flickrSearchResultRaw)
    }

    @Test
    fun `calling getMovieImages from repo with red service should return single of error `() {
        val throwable = Throwable()
        flickrApiServiceSingleSubject.onError(throwable)

        repo.getMovieImages("")
            .test()
            .assertError(throwable)
    }

}
