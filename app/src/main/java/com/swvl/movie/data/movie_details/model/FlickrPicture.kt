package com.swvl.movie.data.movie_details.model

import com.google.gson.annotations.SerializedName

class FlickrPicture(
    @SerializedName("id") val id: String? = null,
    @SerializedName("secret") val secret: String? = null,
    @SerializedName("server") val server: String? = null,
    @SerializedName("farm") val farm: String? = null
)
