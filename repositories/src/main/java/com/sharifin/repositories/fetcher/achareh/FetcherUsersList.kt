package com.sharifin.repositories.fetcher.achareh

import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.defaultRetrofitRetry
import com.sharifin.servermodel.AcharehRegisterServerModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class FetcherUsersList @Inject constructor(
    private val api : AcharehApi
) : Fetcher<Unit, Response<ArrayList<AcharehRegisterServerModel>>> {
    override fun fetch(key: Unit): Single<Response<ArrayList<AcharehRegisterServerModel>>> =
        api.getUsersList()
            .defaultRetrofitRetry()
}