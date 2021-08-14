package com.swvl.movie.presentation.movies_details.ui

import android.os.Bundle
import android.view.View
import com.swvl.movie.R
import com.swvl.movie.application.MovieApplication
import com.swvl.movie.base.activity.BaseViewModelActivity
import com.swvl.movie.di.movie_details.MovieDetailsScreenDataModule
import com.swvl.movie.presentation.movies_details.viewmodel.MovieDetailsViewModel
import com.swvl.movie.presentation.movies_details.viewmodel.MovieDetailsViewState
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseViewModelActivity<MovieDetailsViewModel>() {
    val movieTitle by lazy { intent.extras?.getString(TITLE_VALUE_KEY) }
    val movieYear by lazy { intent.extras?.getString(YEAR_VALUE_KEY) }
    val movieGenres by lazy { intent.extras?.getString(GENRES_VALUE_KEY) }
    val movieCast by lazy { intent.extras?.getString(CAST_VALUE_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        displayMovieDetails()
        viewModel.getMovieImages(movieTitle ?: "")
    }

    private fun displayMovieDetails() {
        supportActionBar?.title= movieTitle
        movieDetailsYear.text = movieYear
        movieDetailsGenres.text = movieGenres
        movieDetailsCast.text = movieCast
    }


    override fun initializeComponent() {
        return MovieApplication.component
            .movieDetailScreenComponent(MovieDetailsScreenDataModule())
            .inject(this)
    }

    override fun getViewModelClass(): Class<MovieDetailsViewModel> {
        return MovieDetailsViewModel::class.java
    }

    override fun observeLiveData() {
        this.viewModel.movieDetailsVIewState.observe(this, { handleViewState(it) })
    }

    private fun handleViewState(state: MovieDetailsViewState) {
        when (state) {
            is MovieDetailsViewState.Loading -> handleLoading()
            is MovieDetailsViewState.Empty -> handleEmpty()
            is MovieDetailsViewState.Error -> handleError(state)
            is MovieDetailsViewState.Content -> handleContent(state)
        }
    }

    private fun handleLoading() {
        detailsRecyclerView.visibility = View.GONE
        detailsProgressBar.visibility = View.VISIBLE
    }

    private fun handleEmpty() {
        imageListError.setText(R.string.empty_message_movie_list)
        detailsRecyclerView.visibility = View.GONE
        detailsProgressBar.visibility = View.GONE
        imageListError.visibility = View.VISIBLE
    }

    private fun handleError(error: MovieDetailsViewState.Error) {
        imageListError.text = error.message
        detailsRecyclerView.visibility = View.GONE
        detailsProgressBar.visibility = View.GONE
        imageListError.visibility = View.VISIBLE
    }

    private fun handleContent(content: MovieDetailsViewState.Content) {
        val adapter = MovieDetailsAdapter(content.flickrSearchResultViewEntity.movieUrls)
        detailsRecyclerView.adapter = adapter
        detailsRecyclerView.visibility = View.VISIBLE
        imageListError.visibility = View.GONE
        detailsProgressBar.visibility = View.GONE
    }
}

const val TITLE_VALUE_KEY = "title_key"
const val YEAR_VALUE_KEY = "year_key"
const val GENRES_VALUE_KEY = "genres_key"
const val CAST_VALUE_KEY = "cast_key"
const val MOVIE_DETAILS_ACTIVITY_ACTION="presentation.movies_details.ui.MovieDetailsActivity"
