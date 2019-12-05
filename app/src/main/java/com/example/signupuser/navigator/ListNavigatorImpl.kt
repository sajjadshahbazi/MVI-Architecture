package com.example.signupuser.navigator

import android.content.Context
import android.os.Bundle
import javax.inject.Inject

class ListNavigatorImpl @Inject constructor(unAuthorizedHandler: UnAuthorizedHandler) : ListNavigator(unAuthorizedHandler) {
    override fun finishModule(context: Context, bundle: Bundle) {

    }
}
