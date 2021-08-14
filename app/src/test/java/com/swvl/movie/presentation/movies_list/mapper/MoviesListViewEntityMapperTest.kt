package com.swvl.movie.presentation.movies_list.mapper

import com.swvl.movie.data.movies_list.model.MovieRaw
import com.swvl.movie.data.movies_list.model.MovieRawItem
import com.swvl.movie.presentation.movies_list.MoviesListItemVIewEntity
import com.swvl.movie.presentation.movies_list.MoviesListViewEntity
import org.junit.Assert
import org.junit.Test

class MoviesListViewEntityMapperTest {
    private val mapper = MoviesListViewEntityMapper()

    private val movieRawItem1 = MovieRawItem("a", "2009", emptyList(), emptyList(), 10)
    private val movieRawItem2 = MovieRawItem("a", "2009", emptyList(), emptyList(), 9)
    private val movieRawItem3 = MovieRawItem("a", "2009", emptyList(), emptyList(), 8)
    private val sortedMovieRaw = MovieRaw(
        listOf(
            movieRawItem1,
            movieRawItem2,
            movieRawItem3
        )
    )
    private val moviesListItemVIewEntity = listOf(
        MoviesListItemVIewEntity("a", "2009", emptyList(), emptyList()),
        MoviesListItemVIewEntity("a", "2009", emptyList(), emptyList()),
        MoviesListItemVIewEntity("a", "2009", emptyList(), emptyList()),
    )

    private val expected = MoviesListViewEntity(mapOf("2009" to moviesListItemVIewEntity))

    @Test
    fun `calling apply should map MoviesListViewEntity`(){
        val actual = mapper.apply(sortedMovieRaw)

        Assert.assertEquals(expected, actual)
    }
}
