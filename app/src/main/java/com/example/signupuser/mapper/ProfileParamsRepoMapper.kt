package com.example.signupuser.mapper

import com.example.signupuser.model.ProfileParamsModel
import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterParamRepoModel
import javax.inject.Inject

class ProfileParamsRepoMapper @Inject constructor() : Mapper<
        ProfileParamsModel,
        RegisterParamRepoModel
        > {
    override fun map(item: ProfileParamsModel): RegisterParamRepoModel =
        RegisterParamRepoModel(
            region = item.region,
            address = item.address,
            lat = item.currentLocationLocalModel.lat,
            lng = item.currentLocationLocalModel.lng,
            coordinateMobile = item.coordinateMobile,
            coordinatePhoneNumber = item.coordinatePhoneNumber,
            firstName = item.firstName,
            lastName = item.lastName,
            gender = item.gender
        )
}