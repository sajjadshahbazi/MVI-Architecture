package com.example.signupuser.navigator

import android.content.Context
import com.sharifin.navigation.Navigation
import com.sharifin.navigation.UnAuthorizedHandler
import javax.inject.Inject

class UnAuthorizedHandler @Inject constructor(
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