package com.swvl.movie.data.movie_details.model

import com.google.gson.annotations.SerializedName

data class FlickrSearchResultRaw(
    @SerializedName("photos") val metadata: FlickrPicturesMetadata? = null
)
