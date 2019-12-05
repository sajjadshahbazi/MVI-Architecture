package com.sharifin.repositories.android

import io.reactivex.Single

interface AndroidRepo {

    fun getAppVersion() : Single<String>
}