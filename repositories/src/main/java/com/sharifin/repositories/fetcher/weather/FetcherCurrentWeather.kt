package com.sharifin.repositories.fetcher.weather

import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.defaultRetrofitRetry
import com.sharifin.servermodel.requestmodels.WeatherRequestServerModel
import com.sharifin.servermodel.responsemodels.CurrentWeatherServerModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class FetcherCurrentWeather @Inject constructor(
        private val api : WeatherApi
) : Fetcher<
        WeatherRequestServerModel,
        Response<CurrentWeatherServerModel>
        > {
    override fun fetch(
            key: WeatherRequestServerModel
    ): Single<Response<CurrentWeatherServerModel>> = api.getCurrentWeather(
                    id = key.id,
                    appId = key.appId
            ).defaultRetrofitRetry()
}