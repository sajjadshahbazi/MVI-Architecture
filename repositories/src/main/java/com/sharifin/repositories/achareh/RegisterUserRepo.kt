package com.sharifin.repositories.achareh

import com.sharifin.repositories.achareh.repomodel.RegisterParamRepoModel
import io.reactivex.Single

interface RegisterUserRepo {
    fun register(registerParamRepoModel : RegisterParamRepoModel): Single<RegisterUserRepoOutputModel>
}