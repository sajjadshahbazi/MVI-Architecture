package com.sharifin.navigation

import android.content.Context
import javax.inject.Inject

class MobankUnAuthorizedHandler @Inject constructor(
) : UnAuthorizedHandler {
    override fun userUnAuthorized(context: Context, nav: Navigation, message: String) {
//        nav.goto(
//            context,
//            Screen.Authentication.LoginScreen(
//                LoginLocalModel(
//                    hasMessage = true,
//                    message = message
//                )
//            )
//        )
    }
}