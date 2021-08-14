package com.swvl.movie.presentation.movies_list.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.swvl.movie.R
import com.swvl.movie.application.MovieApplication.Companion.component
import com.swvl.movie.base.activity.BaseViewModelActivity
import com.swvl.movie.di.movies_list.MoviesListModule
import com.swvl.movie.presentation.movies_list.ui.section.MovieSectionAdapter
import com.swvl.movie.presentation.movies_list.viewmodel.MoviesListViewModel
import com.swvl.movie.presentation.movies_list.viewmodel.MoviesListViewState
import kotlinx.android.synthetic.main.activity_main.*


class MoviesListActivity : BaseViewModelActivity<MoviesListViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initializeComponent() {
        return component
            .moviesListScreenComponent(MoviesListModule())
            .inject(this)

    }

    override fun getViewModelClass(): Class<MoviesListViewModel> {
        return MoviesListViewModel::class.java
    }

    override fun observeLiveData() {
        this.viewModel.moviesListViewState.observe(this, { handleViewState(it) })
    }

    private fun handleViewState(state: MoviesListViewState) {
        when (state) {
            is MoviesListViewState.Loading -> handleLoading()
            is MoviesListViewState.Empty -> handleEmpty()
            is MoviesListViewState.Error -> handleError(state)
            is MoviesListViewState.Content -> handleContent(state)
        }
    }

    private fun handleLoading() {
        listError.visibility = View.GONE
        venueList.visibility = View.GONE
        listLoader.visibility = View.VISIBLE
    }

    private fun handleEmpty() {
        listError.setText(R.string.empty_message)
        venueList.visibility = View.GONE
        listLoader.visibility = View.GONE
        listError.visibility = View.VISIBLE
    }

    private fun handleError(error: MoviesListViewState.Error) {
        showErrorMessage(error.message)
    }

    private fun showErrorMessage(errorMessage: String) {
        listError.text = errorMessage
        venueList.visibility = View.GONE
        listLoader.visibility = View.GONE
        listError.visibility = View.VISIBLE
    }

    private fun handleContent(content: MoviesListViewState.Content) {
        venueList.layoutManager = LinearLayoutManager(this)
        val adapter = MovieSectionAdapter(content.moviesListViewState)
        venueList.adapter = adapter
        venueList.visibility = View.VISIBLE
        listError.visibility = View.GONE
        listLoader.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "search herer"
        searchView.clearFocus()
        searchView.setOnQueryTextListener(onQueryTextListener())
        return true
    }

    private fun onQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?) = false
        override fun onQueryTextChange(newText: String): Boolean {
            viewModel.applySearchTerm(newText)
            return false
        }
    }

}
