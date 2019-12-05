package com.sharifin.repositories.achareh.mapper

import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterParamRepoModel
import com.sharifin.servermodel.requestmodels.AcharehRegisterRequestModel
import javax.inject.Inject

class RegisterParamRepoMapper @Inject constructor() :
    Mapper<RegisterParamRepoModel, AcharehRegisterRequestModel> {
    override fun map(item: RegisterParamRepoModel): AcharehRegisterRequestModel =
        AcharehRegisterRequestModel(
            region = item.region,
            address = item.address,
            lat = item.lat,
            lng = item.lng,
            coordinateMobile = item.coordinateMobile,
            coordinatePhoneNumber = item.coordinatePhoneNumber,
            firstName = item.firstName,
            lastName = item.lastName,
            gender = item.gender
        )
}