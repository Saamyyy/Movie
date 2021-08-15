package com.swvl.movie.domain.movies_detilas

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.swvl.movie.data.movie_details.MovieImagesRepository
import com.swvl.movie.data.movie_details.model.FlickrSearchResultRaw
import com.swvl.movie.domain.movies_detilas.mapper.FlickrSearchResultDomainEntityMapper
import com.swvl.movie.domain.movies_detilas.model.FlickrSearchResultDomainEntity
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Test

class FetchMovieImagesTest {
    private val flickrSearchResultDomainEntityMapper: FlickrSearchResultDomainEntityMapper = mock()
    private val movieImagesRepository: MovieImagesRepository = mock()

    private val fetchMovieImages =
        FetchMovieImages(flickrSearchResultDomainEntityMapper, movieImagesRepository)
    private val repoSingleSubject = SingleSubject.create<FlickrSearchResultRaw>()

    @Before
    fun setUp() {
        whenever(movieImagesRepository.getMovieImages(any())).thenReturn(repoSingleSubject)
    }

    @Test
    fun `calling getMovieImageUrls with green repo and mapper should return single of FlickrSearchResultDomainEntity`() {
        val raw = FlickrSearchResultRaw()
        repoSingleSubject.onSuccess(raw)
        whenever(flickrSearchResultDomainEntityMapper.apply(raw)).thenReturn(
            FlickrSearchResultDomainEntity(listOf())
        )
        fetchMovieImages
            .getMovieImageUrls("")
            .test()
            .assertValue(FlickrSearchResultDomainEntity(listOf()))

    }

    @Test
    fun `calling getMovieImageUrls with red repo  should return single of error`() {
        val throwable = Throwable()
        repoSingleSubject.onError(throwable)
        fetchMovieImages
            .getMovieImageUrls("")
            .test()
            .assertError(throwable)

    }

}
