package com.example.signupuser.di.weather

import androidx.lifecycle.ViewModel
import com.example.signupuser.action.WeatherAction
import com.example.signupuser.processor.WeatherProcessor
import com.example.signupuser.result.WeatherResult
import com.example.signupuser.view.weather.mapper.CurrentWeatherRepoUiModelMapper
import com.example.signupuser.view.weather.mapper.DailyWeatherRepoUiMapper
import com.example.signupuser.view.weather.mapper.HourlyWeatherRepoUiMapper
import com.example.signupuser.view.weather.models.CurrentWeatherUiModel
import com.example.signupuser.view.weather.models.DailyUiModel
import com.example.signupuser.view.weather.models.HourlyUiModel
import com.example.signupuser.viewmodels.WeatherViewModel
import com.sharifin.base.ViewModelKey
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.CurrentWeatherRepoModel
import com.sharifin.repositories.weather.repomodel.WeatherDailyDetailsRepoModel
import com.sharifin.repositories.weather.repomodel.WeatherHourlyDetailsRepoModel
import com.sharifin.servermodel.responsemodels.WeatherDailyDetailsServerModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeatherViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun postWeatherViewModelModule(
        viewModel: WeatherViewModel
    ): ViewModel

    @Binds
    internal abstract fun postWeatherProcessor(
        processor: WeatherProcessor
    ): MviProcessor<WeatherAction, WeatherResult>

    @Binds
    internal abstract fun postCurrentWeatherRepoUiModelMapper(
        mapper : CurrentWeatherRepoUiModelMapper
    ): Mapper<
            CurrentWeatherRepoModel,
            CurrentWeatherUiModel
            >

    @Binds
    internal abstract fun postDailyWeatherRepoUiMapper(
        mapper : DailyWeatherRepoUiMapper
    ): Mapper<
            WeatherDailyDetailsRepoModel,
            DailyUiModel
            >

    @Binds
    internal abstract fun postHourlyWeatherRepoUiMapper(
        mapper : HourlyWeatherRepoUiMapper
    ): Mapper<
            WeatherHourlyDetailsRepoModel,
            HourlyUiModel
            >

}