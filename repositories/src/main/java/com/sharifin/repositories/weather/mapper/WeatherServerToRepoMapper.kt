package com.sharifin.repositories.weather.mapper

import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.WeatherRepoModel
import com.sharifin.servermodel.responsemodels.WeatherServerModel
import javax.inject.Inject

class WeatherServerToRepoMapper @Inject constructor() : Mapper<
        WeatherServerModel,
        WeatherRepoModel
        > {
    override fun map(
            item: WeatherServerModel
    ): WeatherRepoModel = WeatherRepoModel(
            id = item.id ?: -1,
            main = item.main ?: "",
            description = item.description ?: "",
            icon = item.icon ?: ""
    )

}