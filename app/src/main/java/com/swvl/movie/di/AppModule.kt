package com.swvl.movie.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.adyen.android.assignment.di.ApplicationScope
import com.swvl.movie.BuildConfig
import com.swvl.movie.common.IStringProvider
import com.swvl.movie.common.StringProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val context: Context) {

    @Provides
    @ApplicationScope
    fun provideContext(): Context {
        return context
    }

    @Provides
    @ApplicationScope
    fun provideStringProvider(stringProvider: StringProvider): IStringProvider {
        return stringProvider
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(gson: Gson): Retrofit {
        val client =
            OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level =
                    HttpLoggingInterceptor.Level.BODY
                })
                .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.FLIKER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

}
