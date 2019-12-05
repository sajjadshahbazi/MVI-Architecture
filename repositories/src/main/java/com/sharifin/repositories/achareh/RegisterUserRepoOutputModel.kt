package com.sharifin.repositories.achareh

import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import com.sharifin.retrofit.ErrorHolder

sealed class RegisterUserRepoOutputModel {
    class Success(val registerNewUserRepoModel : RegisterNewUserRepoModel): RegisterUserRepoOutputModel()
    class Error(val err: ErrorHolder): RegisterUserRepoOutputModel()
}