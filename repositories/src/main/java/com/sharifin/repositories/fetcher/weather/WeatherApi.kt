package com.sharifin.repositories.fetcher.weather

import com.sharifin.servermodel.responsemodels.CurrentWeatherServerModel
import com.sharifin.servermodel.responsemodels.DailyWeatherServerModel
import com.sharifin.servermodel.responsemodels.HourlyWeatherServerModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    fun getCurrentWeather(
            @Query("id", encoded = true) id : Int,
            @Query("appid") appId : String

    ): Single<Response<CurrentWeatherServerModel>>

    @GET("/data/2.5/forecast/daily")
    fun getDailyWeather(
            @Query("id", encoded = true) id : Int,
            @Query("appid") appId : String
    ): Single<Response<DailyWeatherServerModel>>

    @GET("/data/2.5/forecast/hourly")
    fun getHourlyWeather(
            @Query("id", encoded = true) id : Int,
            @Query("appid") appId : String
    ): Single<Response<HourlyWeatherServerModel>>
}