package com.example.signupuser.app_di


import com.example.signupuser.navigator.*
import com.sharifin.navigation.MobankUnAuthorizedHandler
import com.sharifin.navigation.UnAuthorizedHandler

import dagger.Binds
import dagger.Module


@Module
abstract class NavigatorModule {

    @Binds
    abstract fun bindUnAuthorized(handler: MobankUnAuthorizedHandler):
            UnAuthorizedHandler

    @Binds
    abstract fun bindProfileNavigator(nav: ProfileNavigatorImpl): ProfileNavigator

    @Binds
    abstract fun bindMapNavigator(nav: MapNavigatorImpl): MapNavigator

    @Binds
    abstract fun bindListNavigator(nav: ListNavigatorImpl): ListNavigator
}