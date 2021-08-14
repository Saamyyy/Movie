package com.swvl.movie.common

data class AppErrorModel(val status:String, val errorMessage:String):Throwable()
