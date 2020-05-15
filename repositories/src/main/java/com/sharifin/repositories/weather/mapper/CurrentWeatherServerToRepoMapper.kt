package com.sharifin.repositories.weather.mapper

import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.CurrentWeatherRepoModel
import com.sharifin.repositories.weather.repomodel.MainRepoModel
import com.sharifin.repositories.weather.repomodel.WeatherRepoModel
import com.sharifin.servermodel.responsemodels.CurrentWeatherServerModel
import com.sharifin.servermodel.responsemodels.MainServerModel
import com.sharifin.servermodel.responsemodels.WeatherServerModel
import javax.inject.Inject

class CurrentWeatherServerToRepoMapper @Inject constructor(
        private val mainMapper: Mapper<
                MainServerModel,
                MainRepoModel
                >,
        private val weatherMapper: Mapper<
                WeatherServerModel,
                WeatherRepoModel
                >
) : Mapper<
        CurrentWeatherServerModel,
        CurrentWeatherRepoModel
        > {
    override fun map(
            item: CurrentWeatherServerModel
    ): CurrentWeatherRepoModel =
        CurrentWeatherRepoModel(
                weather = (item.weather?:ArrayList()).map {
                    weatherMapper.map(it)
                } as ArrayList<WeatherRepoModel>,
                base = item.base ?: "",
                main = mainMapper.map(
                        item.main ?: MainServerModel()
                ),
                visibility = item.visibility ?: -1,
                dt = item.dt ?: -1,
                id = item.id ?: -1,
                name = item.name ?: "",
                cod = item.cod ?: -1
        )
}