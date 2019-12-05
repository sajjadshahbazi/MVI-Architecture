package com.example.signupuser.mapper

import com.example.signupuser.model.UserUiModel
import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import javax.inject.Inject

class UserRepoUiMapper @Inject constructor() : Mapper<
        RegisterNewUserRepoModel,
        UserUiModel>{
    override fun map(item: RegisterNewUserRepoModel): UserUiModel =
        UserUiModel(
            id = item.id.toString(),
            firstName = item.firstName,
            lastName = item.lastName,
            address = item.address,
            coordinateMobile = item.coordinateMobile
        )
}