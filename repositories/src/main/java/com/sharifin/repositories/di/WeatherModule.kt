package com.sharifin.repositories.di

import com.sharifin.core.Mapper
import com.sharifin.core.create
import com.sharifin.repositories.fetcher.weather.*
import com.sharifin.repositories.weather.mapper.*
import com.sharifin.repositories.weather.repomodel.*
import com.sharifin.repository.Fetcher
import com.sharifin.servermodel.requestmodels.WeatherRequestServerModel
import com.sharifin.servermodel.responsemodels.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Response
import retrofit2.Retrofit

@Module
abstract class WeatherModule {

    @Binds
    abstract fun bindWeatherServerToRepoMapper(
            mapper: WeatherServerToRepoMapper
    ): Mapper<
            WeatherServerModel,
            WeatherRepoModel
            >

    @Binds
    abstract fun bindCurrentWeatherServerToRepoMapper(
            mapper: CurrentWeatherServerToRepoMapper
    ): Mapper<
            CurrentWeatherServerModel,
            CurrentWeatherRepoModel
            >

    @Binds
    abstract fun bindDailyWeatherServerToRepoMapper(
            mapper: DailyWeatherServerToRepoMapper
    ): Mapper<
            DailyWeatherServerModel,
            DailyWeatherRepoModel
            >

    @Binds
    abstract fun bindWeatherDailyDetailsServerRepoMapper(
            mapper: WeatherDailyDetailsServerRepoMapper
    ): Mapper<
            WeatherDailyDetailsServerModel,
            WeatherDailyDetailsRepoModel
            >

    @Binds
    abstract fun bindCityMapper(
            mapper: CityMapper
    ): Mapper<
            CityServerModel,
            CityRepoModel
            >

    @Binds
    abstract fun bindTempMapper(
            mapper: TempMapper
    ): Mapper<
            TempServerModel,
            TempRepoModel
            >

    @Binds
    abstract fun bindMainWeatherServerToRepoMapper(
            mapper: MainWeatherServerToRepoMapper
    ): Mapper<
            MainServerModel,
            MainRepoModel
            >
    @Binds
    abstract fun bindHourlyWeatherServerToRepoMapper(
            mapper: HourlyWeatherServerToRepoMapper
    ): Mapper<
            HourlyWeatherServerModel,
            HourlyWeatherRepoModel
            >

    @Binds
    abstract fun bindWeatherHourlyDetailsServerRepoMapper(
            mapper: WeatherHourlyDetailsServerRepoMapper
    ): Mapper<
            WeatherHourlyDetailsServerModel,
            WeatherHourlyDetailsRepoModel
            >

    @Binds
    abstract fun bindFetcherCurrentWeather(
            fetcher: FetcherCurrentWeather
    ): Fetcher<
            WeatherRequestServerModel,
            Response<CurrentWeatherServerModel>
            >
    @Binds
    abstract fun bindFetcherDailyWeather(
            fetcher: FetcherDailyWeather
    ): Fetcher<
            WeatherRequestServerModel,
            Response<DailyWeatherServerModel>
            >

    @Binds
    abstract fun bindFetcherHourlyWeather(
            fetcher: FetcherHourlyWeather
    ): Fetcher<
            WeatherRequestServerModel,
            Response<HourlyWeatherServerModel>
            >

    @Binds
    abstract fun bindWeatherUserRepoImpl(
            mapper: WeatherRepoImpl
    ): WeatherRepo

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideWeatherApi(
                retrofit: Retrofit
        ): WeatherApi = retrofit.create()
    }
}