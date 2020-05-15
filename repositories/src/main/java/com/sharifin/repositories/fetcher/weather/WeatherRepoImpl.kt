package com.sharifin.repositories.fetcher.weather

import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.CurrentWeatherRepoModel
import com.sharifin.repositories.weather.repomodel.DailyWeatherRepoModel
import com.sharifin.repositories.weather.repomodel.HourlyWeatherRepoModel
import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.ErrorHolder
import com.sharifin.retrofit.isNotSuccessful
import com.sharifin.retrofit.retrofitutils.getErrorHolder
import com.sharifin.servermodel.requestmodels.WeatherRequestServerModel
import com.sharifin.servermodel.responsemodels.CurrentWeatherServerModel
import com.sharifin.servermodel.responsemodels.DailyWeatherServerModel
import com.sharifin.servermodel.responsemodels.HourlyWeatherServerModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
        private val fetcher: Fetcher<
                WeatherRequestServerModel,
                Response<CurrentWeatherServerModel>
                >,
        private val currentMapper: Mapper<
                CurrentWeatherServerModel,
                CurrentWeatherRepoModel
                >,
        private val hourlyFetcher: Fetcher<
                WeatherRequestServerModel,
                Response<HourlyWeatherServerModel>
                >,
        private val hourlyMapper: Mapper<
                HourlyWeatherServerModel,
                HourlyWeatherRepoModel
                >,
        private val dailyMapper: Mapper<
                DailyWeatherServerModel,
                DailyWeatherRepoModel
                >,
        private val dailyFetcher: Fetcher<
                WeatherRequestServerModel,
                Response<DailyWeatherServerModel>
                >
) : WeatherRepo {

    override fun getCurrentWeather(): Single<CurrentWeatherRepoOutputModel> =
            fetcher.fetch(WeatherRequestServerModel())
                    .map {
                        it.takeIf { it.isNotSuccessful }?.let {
                            return@map CurrentWeatherRepoOutputModel.Error(it.getErrorHolder())
                        }
                        val body = it.body()
                                ?: return@map CurrentWeatherRepoOutputModel.Error(
                                        ErrorHolder.Message(
                                                "خطای سرور"
                                        )
                                )
                        CurrentWeatherRepoOutputModel.Success(
                                currentMapper.map(body)
                        )

                    }

    override fun getDailyWeather(): Single<DailyWeatherRepoOutputModel> =
            dailyFetcher.fetch(WeatherRequestServerModel())
                    .map {
                        it.takeIf { it.isNotSuccessful }?.let {
                            return@map DailyWeatherRepoOutputModel.Error(it.getErrorHolder())
                        }
                        val body = it.body()
                                ?: return@map DailyWeatherRepoOutputModel.Error(
                                        ErrorHolder.Message(
                                                "خطای سرور"
                                        )
                                )
                        DailyWeatherRepoOutputModel.Success(
                                dailyMapper.map(body)
                        )

                    }


    override fun getHourlyWeather(): Single<HourlyWeatherRepoOutputModel> =
            hourlyFetcher.fetch(WeatherRequestServerModel())
                    .map {
                        it.takeIf { it.isNotSuccessful }?.let {
                            return@map HourlyWeatherRepoOutputModel.Error(it.getErrorHolder())
                        }
                        val body = it.body()
                                ?: return@map HourlyWeatherRepoOutputModel.Error(
                                        ErrorHolder.Message(
                                                "خطای سرور"
                                        )
                                )
                        HourlyWeatherRepoOutputModel.Success(
                                hourlyMapper.map(body)
                        )

                    }
}