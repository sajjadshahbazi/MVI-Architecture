package com.sharifin.repositories.fetcher.achareh

import com.sharifin.servermodel.AcharehRegisterServerModel
import com.sharifin.servermodel.requestmodels.AcharehRegisterRequestModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AcharehApi{
    @POST("/api/karfarmas/address")
    fun registerUser(
        @Body body: AcharehRegisterRequestModel
    ): Single<Response<AcharehRegisterServerModel>>

    @GET("/api/karfarmas/address")
    fun getUsersList(
    ): Single<Response<ArrayList<AcharehRegisterServerModel>>>
}