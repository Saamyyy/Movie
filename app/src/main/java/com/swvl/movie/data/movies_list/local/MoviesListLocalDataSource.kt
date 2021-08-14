package com.swvl.movie.data.movies_list.local

import android.content.Context
import com.google.gson.Gson
import com.swvl.movie.data.movies_list.model.MovieRaw
import io.reactivex.Single
import okio.buffer
import okio.source
import java.nio.charset.Charset
import javax.inject.Inject

class MoviesListLocalDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    fun getMoviesList() =
        context.assets.open(MOVIES_LIST_FILE_NAME).source().buffer().use {
            val jsonString = it.readByteString().string(Charset.defaultCharset())
            Single.just(gson.fromJson(jsonString, MovieRaw::class.java))
        }
}

private const val MOVIES_LIST_FILE_NAME = "movies.json"
