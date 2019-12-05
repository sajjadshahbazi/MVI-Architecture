package com.sharifin.repositories.fetcher.achareh

import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.defaultRetrofitRetry
import com.sharifin.servermodel.AcharehRegisterServerModel
import com.sharifin.servermodel.requestmodels.AcharehRegisterRequestModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class FetcherRegisterProfile @Inject constructor(
    private val api : AcharehApi
) : Fetcher<AcharehRegisterRequestModel, Response<AcharehRegisterServerModel>> {
    override fun fetch(key: AcharehRegisterRequestModel): Single<Response<AcharehRegisterServerModel>> =
        api.registerUser(key)
            .defaultRetrofitRetry()
}