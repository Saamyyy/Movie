package com.swvl.movie.common.navigation

import android.content.Context
import android.content.Intent

object Navigator {

    fun navigate(context: Context, navigatorModel: NavigatorModel) {
        launchActivityWithAction(context, navigatorModel)
    }

    private fun launchActivityWithAction(
        context: Context,
        intentNavigatorModel: NavigatorModel
    ) {
        val intent = newIntent(context, intentNavigatorModel.action)

        intent.putExtras(intentNavigatorModel.options)

        context.startActivity(intent)
    }

    private fun newIntent(context: Context, action: String?):
            Intent = Intent(action).setPackage(context.packageName)
}
