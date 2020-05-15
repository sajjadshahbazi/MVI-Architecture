package com.sharifin.repositories.fetcher.weather

import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.defaultRetrofitRetry
import com.sharifin.servermodel.requestmodels.WeatherRequestServerModel
import com.sharifin.servermodel.responsemodels.HourlyWeatherServerModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class FetcherHourlyWeather @Inject constructor(
        private val api : WeatherApi
) : Fetcher<
        WeatherRequestServerModel,
        Response<HourlyWeatherServerModel>
        > {
    override fun fetch(
            key: WeatherRequestServerModel
    ): Single<Response<HourlyWeatherServerModel>> = api.getHourlyWeather(
            id = key.id,
            appId = key.appId
    ).defaultRetrofitRetry()
}