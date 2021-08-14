package com.swvl.movie.common

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

/**
 * Helper to provide strings from the android resources
 */
class StringProvider @Inject constructor(private var context: Context) : IStringProvider {

    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}

/**
 * Helper to provide strings from the android resources
 */
interface IStringProvider {
    fun getString(@StringRes resourceId: Int): String
}
