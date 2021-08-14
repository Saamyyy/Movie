package com.swvl.movie.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.swvl.movie.di.AppComponent
import com.swvl.movie.di.AppModule
import com.swvl.movie.di.DaggerAppComponent

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        component = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var component: AppComponent
    }
}
