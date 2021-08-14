package com.swvl.movie.common.navigation

import android.content.Context
import android.content.Intent
import javax.inject.Inject

class Navigator @Inject constructor(
    val context: Context
) {

    fun navigate(navigatorModel: NavigatorModel) {
        launchActivityWithAction(navigatorModel)
    }

    private fun launchActivityWithAction(
        intentNavigatorModel: NavigatorModel
    ) {
        val intent = newIntent(context, intentNavigatorModel.action)

        intent.putExtras(intentNavigatorModel.options)

        context.startActivity(intent)
    }

    private fun newIntent(context: Context, action: String?):
            Intent = Intent(action).setPackage(context.packageName)
}
