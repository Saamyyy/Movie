package com.swvl.movie.common.navigation

import android.os.Bundle

data class NavigatorModel @JvmOverloads constructor(
    var action: String = "",
    var options: Bundle = Bundle(),
)
