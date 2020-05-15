package com.sharifin.repositories.fetcher.weather

import com.sharifin.repositories.weather.repomodel.HourlyWeatherRepoModel
import com.sharifin.retrofit.ErrorHolder

sealed class HourlyWeatherRepoOutputModel {
    class Success(
            val weather : HourlyWeatherRepoModel
    ): HourlyWeatherRepoOutputModel()

    class Error(
            val err: ErrorHolder
    ): HourlyWeatherRepoOutputModel()
}