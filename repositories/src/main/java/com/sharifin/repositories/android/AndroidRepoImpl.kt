package com.sharifin.repositories.android

import android.content.Context
import com.sharifin.core.getAppVersion
import io.reactivex.Single
import javax.inject.Inject

class AndroidRepoImpl @Inject constructor(
        private val context : Context
): AndroidRepo{
    override fun getAppVersion(): Single<String> =
            Single.just(context.getAppVersion())

}