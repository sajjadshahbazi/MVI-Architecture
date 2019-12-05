package com.sharifin.navigation

import com.sharifin.navigation.model.LoginLocalModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationImpl @Inject constructor(
    override val stack: NavigationStack
) : DefaultNavigation() {

    init {
        stack.push(Screen.Authentication.LoginScreen(LoginLocalModel()))
    }
}