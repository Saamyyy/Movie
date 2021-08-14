package com.swvl.movie.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.swvl.movie.base.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class BaseViewModelActivity<T : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeComponent()
        initializeViewModel()
        observeLiveData()
    }


    /**
     * Initialize dagger Component
     */
    abstract fun initializeComponent()

    /**
     *  start the viewModel Life cycle
     */
    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
        viewModel.onCreate()
    }

    /**
     * get the ::class.java of the view model
     */
    protected abstract fun getViewModelClass(): Class<T>

    /**
     * Observe the ViewModel live data objects
     */
    abstract fun observeLiveData()
}
