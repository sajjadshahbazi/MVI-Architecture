package com.sharifin.repositories.achareh

import io.reactivex.Single

interface UsersListRepo {
    fun getList(): Single<ListRepoOutputModel>

}