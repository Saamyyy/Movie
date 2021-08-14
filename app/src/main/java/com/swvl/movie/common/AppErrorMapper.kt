package com.swvl.movie.common

import com.swvl.movie.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class AppErrorMapper @Inject constructor(private val stringProvider: IStringProvider) {
    fun map(throwable: Throwable) = when (throwable) {
        is ConnectException, is SocketException, is UnknownHostException ->
            AppErrorModel("", stringProvider.getString(R.string.network_error_message))
        is HttpException -> AppErrorModel(throwable.code().toString(),throwable.message())
        else ->  AppErrorModel("",throwable.message?: "")
    }
}
