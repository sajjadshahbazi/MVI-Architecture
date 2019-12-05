package com.example.signupuser.navigator

import android.content.Context
import android.os.Bundle
import javax.inject.Inject

class MapNavigatorImpl @Inject constructor(unAuthorizedHandler: UnAuthorizedHandler) : MapNavigator(unAuthorizedHandler) {
    override fun finishModule(context: Context, bundle: Bundle) {

    }
}
