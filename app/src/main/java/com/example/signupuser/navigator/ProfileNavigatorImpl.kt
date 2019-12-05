package com.example.signupuser.navigator

import android.content.Context
import android.os.Bundle
import javax.inject.Inject

class ProfileNavigatorImpl @Inject constructor(unAuthorizedHandler: UnAuthorizedHandler) : ProfileNavigator(unAuthorizedHandler) {
    override fun finishModule(context: Context, bundle: Bundle) {

    }
}
