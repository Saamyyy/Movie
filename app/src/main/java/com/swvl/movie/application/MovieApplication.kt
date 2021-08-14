package com.swvl.movie.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.adyen.android.assignment.di.AppComponent
import com.adyen.android.assignment.di.DaggerAppComponent
import com.swvl.movie.di.AppModule

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
