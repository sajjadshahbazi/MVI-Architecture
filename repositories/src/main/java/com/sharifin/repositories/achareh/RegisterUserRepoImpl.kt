package com.sharifin.repositories.achareh

import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import com.sharifin.repositories.achareh.repomodel.RegisterParamRepoModel
import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.ErrorHolder
import com.sharifin.retrofit.isNotSuccessful
import com.sharifin.retrofit.retrofitutils.getErrorHolder
import com.sharifin.servermodel.AcharehRegisterServerModel
import com.sharifin.servermodel.requestmodels.AcharehRegisterRequestModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class RegisterUserRepoImpl @Inject constructor(
    private val fetcher: Fetcher<
            AcharehRegisterRequestModel,
            Response<AcharehRegisterServerModel>
            >,
    private val resMapper: Mapper<
            AcharehRegisterServerModel,
            RegisterNewUserRepoModel
            >,
    private val mapper: Mapper<
            RegisterParamRepoModel,
            AcharehRegisterRequestModel
            >
) : RegisterUserRepo {

    override fun register(registerParamRepoModel: RegisterParamRepoModel): Single<RegisterUserRepoOutputModel> =
        fetcher.fetch(mapper.map(registerParamRepoModel))
            .map {
                if (it.isNotSuccessful) {
                    return@map RegisterUserRepoOutputModel.Error(it.getErrorHolder())
                }
                val body = it.body()
                    ?: return@map RegisterUserRepoOutputModel.Error(
                        ErrorHolder.Message(
                            "خطای سرور"
                        )
                    )
                RegisterUserRepoOutputModel.Success(
                    resMapper.map(body)
                )
            }
}