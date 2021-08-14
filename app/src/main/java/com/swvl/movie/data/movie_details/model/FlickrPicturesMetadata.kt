package com.swvl.movie.data.movie_details.model

import com.google.gson.annotations.SerializedName

data class FlickrPicturesMetadata(
    @SerializedName("photo") val photos: List<FlickrPicture>? = null
)
