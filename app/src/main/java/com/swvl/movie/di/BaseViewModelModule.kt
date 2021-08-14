package com.adyen.android.assignment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swvl.movie.base.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module
class BaseViewModelModule {
    @Provides
    fun bindViewModelFactory(map: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>): ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }
}
