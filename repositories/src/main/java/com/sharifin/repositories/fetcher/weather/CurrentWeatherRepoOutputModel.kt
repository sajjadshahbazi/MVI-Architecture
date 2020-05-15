package com.sharifin.repositories.fetcher.weather

import com.sharifin.repositories.weather.repomodel.CurrentWeatherRepoModel
import com.sharifin.retrofit.ErrorHolder

sealed class CurrentWeatherRepoOutputModel {
    class Success(
            val weather : CurrentWeatherRepoModel
    ): CurrentWeatherRepoOutputModel()

    class Error(
            val err: ErrorHolder
    ): CurrentWeatherRepoOutputModel()
}