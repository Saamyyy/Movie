package com.swvl.movie.common.error_mapper

data class AppErrorModel(val status:String, val errorMessage:String):Throwable()
