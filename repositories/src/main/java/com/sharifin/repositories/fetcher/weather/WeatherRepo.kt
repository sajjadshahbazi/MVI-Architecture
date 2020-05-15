package com.sharifin.repositories.fetcher.weather

import io.reactivex.Single

interface WeatherRepo {
    fun getCurrentWeather(): Single<CurrentWeatherRepoOutputModel>
    fun getDailyWeather(): Single<DailyWeatherRepoOutputModel>
    fun getHourlyWeather(): Single<HourlyWeatherRepoOutputModel>
}