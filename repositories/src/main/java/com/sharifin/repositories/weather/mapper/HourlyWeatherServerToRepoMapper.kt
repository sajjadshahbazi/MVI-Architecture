package com.sharifin.repositories.weather.mapper

import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.*
import com.sharifin.servermodel.responsemodels.*
import com.sharifin.servermodel.responsemodels.HourlyWeatherServerModel
import javax.inject.Inject

class HourlyWeatherServerToRepoMapper @Inject constructor(
        private val cityMapper: Mapper<
                CityServerModel,
                CityRepoModel
                >,
        private val weatherMapper: Mapper<
                WeatherHourlyDetailsServerModel,
                WeatherHourlyDetailsRepoModel
                >
) : Mapper<
        HourlyWeatherServerModel,
        HourlyWeatherRepoModel
        > {

    override fun map(
            item: HourlyWeatherServerModel
    ): HourlyWeatherRepoModel =
            HourlyWeatherRepoModel(
                    cod = item.cod,
                    message = item.message,
                    cnt = item.cnt,
                    list = item.list.map {
                        weatherMapper.map(it)
                    } as ArrayList<WeatherHourlyDetailsRepoModel>,
                    city = cityMapper.map(item.city)
            )
}


class WeatherHourlyDetailsServerRepoMapper @Inject constructor(
        private val mainMapper: Mapper<
                MainServerModel,
                MainRepoModel
                >,
        private val weatherMapper: Mapper<
                WeatherServerModel,
                WeatherRepoModel
                >
) : Mapper<
        WeatherHourlyDetailsServerModel,
        WeatherHourlyDetailsRepoModel
        > {
    override fun map(
            item: WeatherHourlyDetailsServerModel
    ): WeatherHourlyDetailsRepoModel =
        WeatherHourlyDetailsRepoModel(
                dt = item.dt ?: -1,
                main = mainMapper.map(item.main ?: MainServerModel()),
                weather = item.weather.map {
                    weatherMapper.map(it)
                } as ArrayList<WeatherRepoModel>,
                dtTxt = item.dt_txt ?: ""
        )

}