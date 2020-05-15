package com.example.signupuser.processor

import com.example.signupuser.action.WeatherAction
import com.example.signupuser.result.WeatherResult
import com.example.signupuser.view.weather.models.CurrentWeatherUiModel
import com.example.signupuser.view.weather.models.DailyUiModel
import com.example.signupuser.view.weather.models.HourlyUiModel
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.Mapper
import com.sharifin.core.defaultClicksLastStableResultWithStart
import com.sharifin.repositories.fetcher.weather.WeatherRepo
import com.sharifin.repositories.fetcher.weather.CurrentWeatherRepoOutputModel
import com.sharifin.repositories.fetcher.weather.DailyWeatherRepoOutputModel
import com.sharifin.repositories.fetcher.weather.HourlyWeatherRepoOutputModel
import com.sharifin.repositories.weather.repomodel.CurrentWeatherRepoModel
import com.sharifin.repositories.weather.repomodel.WeatherDailyDetailsRepoModel
import com.sharifin.repositories.weather.repomodel.WeatherHourlyDetailsRepoModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherProcessor @Inject constructor(
        repo: WeatherRepo,
        currentWeatherMapper: Mapper<
                CurrentWeatherRepoModel,
                CurrentWeatherUiModel
                >,
        hourlyWeatherMapper: Mapper<
                WeatherHourlyDetailsRepoModel,
                HourlyUiModel
                >,
        dailyWeatherMapper : Mapper<
                WeatherDailyDetailsRepoModel,
                DailyUiModel
                >

) : MviProcessor<WeatherAction, WeatherResult> {
    override val actionProcessor: ObservableTransformer<WeatherAction, WeatherResult> =
            ObservableTransformer { actions ->
                actions.publish { publish ->
                    Observable.merge(
                            listOf(
                                    publish.ofType(WeatherAction.Initial::class.java)
                                            .compose(initial)
                            )
                    ).observeOn(AndroidSchedulers.mainThread())
                }
            }

    private val initial =
            ObservableTransformer<WeatherAction.Initial, WeatherResult> { actions ->
                actions.publish { publish ->
                    Observable.merge(
                            listOf(
                                    publish.compose(getCurrentWeather),
                                    publish.compose(getHourlyWeather),
                                    publish.compose(getDailyWeather)
                            )
                    ).delay(300, TimeUnit.MILLISECONDS)
                            .concatWith(publish.compose(lastStable))
                            .startWith(WeatherResult.Loading)
                }
            }


    private val lastStable =
            ObservableTransformer<WeatherAction, WeatherResult.LastStable> { actions ->
                Observable.just(WeatherResult.LastStable)
            }

    private val getCurrentWeather =
            ObservableTransformer<WeatherAction, WeatherResult> { actions ->
                actions.switchMap { action ->
                    repo.getCurrentWeather()
                            .toObservable()
                            .switchMap {
                                when (it) {
                                    is CurrentWeatherRepoOutputModel.Success -> {
                                        Observable.just(
                                                WeatherResult.CurrentWeather(
                                                        currentWeatherMapper.map(it.weather)
                                                )
                                        )
                                    }
                                    is CurrentWeatherRepoOutputModel.Error -> {
                                        defaultClicksLastStableResultWithStart(
                                                WeatherResult.LastStable,
                                                WeatherResult.Error(it.err)
                                        )
                                    }
                                    else ->
                                        throw IllegalArgumentException("this output=$it is not expected here.")
                                }
                            }.startWith(WeatherResult.Loading)
                }
            }

    private val getHourlyWeather =
            ObservableTransformer<WeatherAction, WeatherResult> { actions ->
                actions.switchMap { action ->
                    repo.getHourlyWeather()
                            .toObservable()
                            .switchMap {
                                when (it) {
                                    is HourlyWeatherRepoOutputModel.Success -> {
                                        Observable.just(
                                                WeatherResult.HourlyWeather(
                                                        it.weather.list.map {
                                                            hourlyWeatherMapper.map(it)
                                                        }
                                                )
                                        )
                                    }
                                    is HourlyWeatherRepoOutputModel.Error -> {
                                        defaultClicksLastStableResultWithStart(
                                                WeatherResult.LastStable,
                                                WeatherResult.Error(it.err)
                                        )
                                    }
                                    else ->
                                        throw IllegalArgumentException("this output=$it is not expected here.")
                                }
                            }.startWith(WeatherResult.Loading)
                }
            }

    private val getDailyWeather =
            ObservableTransformer<WeatherAction, WeatherResult> { actions ->
                actions.switchMap { action ->
                    repo.getDailyWeather()
                            .toObservable()
                            .switchMap {
                                when (it) {
                                    is DailyWeatherRepoOutputModel.Success -> {
                                        Observable.just(
                                                WeatherResult.DailyWeather(
                                                        it.weather.list.map { dailyWeatherMapper.map(it) },
                                                        city = it.weather.city?.name?:""
                                                )
                                        )
                                    }
                                    is DailyWeatherRepoOutputModel.Error -> {
                                        defaultClicksLastStableResultWithStart(
                                                WeatherResult.LastStable,
                                                WeatherResult.Error(it.err)
                                        )
                                    }
                                    else ->
                                        throw IllegalArgumentException("this output=$it is not expected here.")
                                }
                            }.startWith(WeatherResult.Loading)
                }
            }
}