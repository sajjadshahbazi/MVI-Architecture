package com.sharifin.repositories.weather.mapper

import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.MainRepoModel
import com.sharifin.servermodel.responsemodels.MainServerModel
import javax.inject.Inject

class MainWeatherServerToRepoMapper @Inject constructor() : Mapper<
        MainServerModel,
        MainRepoModel
        > {
    override fun map(
            item: MainServerModel
    ): MainRepoModel = MainRepoModel(
            temp = item.temp ?: -0.1,
            pressure = item.pressure ?: -0.1,
            humidity = item.humidity ?: -1,
            tempMin = item.tempMin ?: -0.1,
            tempMax = item.tempMax ?: -0.1
    )
}