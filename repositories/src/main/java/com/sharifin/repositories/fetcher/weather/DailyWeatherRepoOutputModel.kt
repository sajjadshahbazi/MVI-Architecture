package com.sharifin.repositories.fetcher.weather

import com.sharifin.repositories.weather.repomodel.DailyWeatherRepoModel
import com.sharifin.retrofit.ErrorHolder

sealed class DailyWeatherRepoOutputModel {
    class Success(
            val weather : DailyWeatherRepoModel
    ): DailyWeatherRepoOutputModel()

    class Error(
            val err: ErrorHolder
    ): DailyWeatherRepoOutputModel()
}