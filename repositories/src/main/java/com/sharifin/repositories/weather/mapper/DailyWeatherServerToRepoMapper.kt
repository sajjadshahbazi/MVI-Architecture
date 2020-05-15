package com.sharifin.repositories.weather.mapper

import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.*
import com.sharifin.servermodel.responsemodels.*
import javax.inject.Inject

class DailyWeatherServerToRepoMapper @Inject constructor(
        private val cityMapper: Mapper<
                CityServerModel,
                CityRepoModel
                >,
        private val weatherMapper: Mapper<
                WeatherDailyDetailsServerModel,
                WeatherDailyDetailsRepoModel
                >
) : Mapper<
        DailyWeatherServerModel,
        DailyWeatherRepoModel
        > {
    override fun map(
            item: DailyWeatherServerModel
    ): DailyWeatherRepoModel =
            DailyWeatherRepoModel(
                    cod = item.cod,
                    message = item.message,
                    city = cityMapper.map(item.city ?: CityServerModel()),
                    cnt = item.cnt ?: -1,
                    list = item.list.map {
                        weatherMapper.map(it)
                    } as ArrayList<WeatherDailyDetailsRepoModel>
            )
}

    class WeatherDailyDetailsServerRepoMapper @Inject constructor(
            private val tempMapper: Mapper<
                    TempServerModel,
                    TempRepoModel
                    >,
            private val weatherMapper: Mapper<
                    WeatherServerModel,
                    WeatherRepoModel
                    >
    ) : Mapper<
            WeatherDailyDetailsServerModel,
            WeatherDailyDetailsRepoModel
            > {
        override fun map(item: WeatherDailyDetailsServerModel): WeatherDailyDetailsRepoModel =
                WeatherDailyDetailsRepoModel(
                        dt = item.dt ?: -1L,
                        temp = tempMapper.map(item.temp ?: TempServerModel()),
                        pressure = item.pressure ?: -0.1,
                        humidity = item.humidity ?: -1,
                        weather = item.weather.map {
                            weatherMapper.map(it)
                        } as ArrayList<WeatherRepoModel>,
                        speed = item.speed ?: -0.1,
                        deg = item.deg ?: -1,
                        clouds = item.clouds ?: -1,
                        snow = item.speed ?: -0.1
                )
    }

    class CityMapper @Inject constructor() : Mapper<
            CityServerModel,
            CityRepoModel
            > {
        override fun map(item: CityServerModel): CityRepoModel =
                CityRepoModel(
                        id = item.id ?: -1L,
                        name = item.name ?: "",
                        country = item.country ?: ""
                )
    }

    class TempMapper @Inject constructor() : Mapper<
            TempServerModel,
            TempRepoModel
            > {
        override fun map(item: TempServerModel): TempRepoModel =
                TempRepoModel(
                        day = item.day ?: -0.1,
                        min = item.min ?: -0.1,
                        max = item.max ?: -0.1,
                        night = item.night ?: -0.1,
                        eve = item.eve ?: -0.1,
                        morn = item.morn ?: -0.1
                )
    }