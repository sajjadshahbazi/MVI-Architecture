package com.sharifin.repositories.fetcher.weather

import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.defaultRetrofitRetry
import com.sharifin.servermodel.requestmodels.WeatherRequestServerModel
import com.sharifin.servermodel.responsemodels.DailyWeatherServerModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class FetcherDailyWeather @Inject constructor(
        private val api : WeatherApi
) : Fetcher<
        WeatherRequestServerModel,
        Response<DailyWeatherServerModel>
        > {
    override fun fetch(
            key: WeatherRequestServerModel
    ): Single<Response<DailyWeatherServerModel>> = api.getDailyWeather(
                    id = key.id,
                    appId = key.appId
            ).defaultRetrofitRetry()
}