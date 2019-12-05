package com.sharifin.repositories.achareh

import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import com.sharifin.retrofit.ErrorHolder

sealed class ListRepoOutputModel {
    class Success(val listUsers : List<RegisterNewUserRepoModel>): ListRepoOutputModel()
    class Error(val err: ErrorHolder): ListRepoOutputModel()
}