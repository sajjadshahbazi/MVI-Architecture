package com.sharifin.repositories.achareh.mapper

import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import com.sharifin.servermodel.AcharehRegisterServerModel
import javax.inject.Inject

class RegisterResponserMapper @Inject constructor() :
    Mapper<AcharehRegisterServerModel, RegisterNewUserRepoModel> {
    override fun map(item: AcharehRegisterServerModel): RegisterNewUserRepoModel =
        RegisterNewUserRepoModel(
            id = item.id,
            address = item.address,
            lastName = item.lastName,
            firstName = item.firstName,
            gender = item.gender,
            lat = item.lat,
            lng = item.lng,
            coordinateMobile = item.coordinateMobile,
            coordinatePhoneNumber = item.coordinatePhoneNumber
        )
}