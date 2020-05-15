package com.example.signupuser.viewmodels

import com.example.signupuser.action.WeatherAction
import com.example.signupuser.intent.WeatherIntent
import com.example.signupuser.result.WeatherResult
import com.example.signupuser.state.WeatherState
import com.sharifin.base.BaseState
import com.sharifin.base.BaseViewModel
import com.sharifin.base.getErrorState
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.notOfType
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

open class WeatherViewModel @Inject constructor(
        private val processor: MviProcessor<WeatherAction, WeatherResult>
) : BaseViewModel<WeatherIntent, WeatherState>() {

    private val intentFilter =
            ObservableTransformer<WeatherIntent, WeatherIntent> { intents ->
                intents.publish { share ->
                    Observable.merge(
                            share.ofType(
                                    WeatherIntent.Initial::class.java
                            ).take(1),
                            share.notOfType(
                                    WeatherIntent.Initial::class.java
                            )
                    )
                }
            }

    private val intentsSubject: PublishSubject<WeatherIntent> = PublishSubject.create()
    override fun processIntents(intents: Observable<WeatherIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<WeatherState> = statesObservable


    private val reducer = BiFunction<
            WeatherState,
            WeatherResult,
            WeatherState
            > { preState, result ->
        when (result) {
            is WeatherResult.LastStable -> {
                preState.copy(
                        base = BaseState.stable()
                )
            }
            is WeatherResult.Error -> {
                preState.copy(
                        base = result.err.getErrorState()
                )
            }
            is WeatherResult.Loading -> {
                preState.copy(
                        base = BaseState.loading()
                )
            }
            is WeatherResult.CurrentWeather -> {
                preState.copy(
                        currentUIWeather = result.currentUiModel
                )
            }
            is WeatherResult.DailyWeather ->{
                preState.copy(
                        dailyUiModel = result.dailyUiModel,
                        city = result.city
                )
            }
            is WeatherResult.HourlyWeather ->{
                preState.copy(
                        hourlyUiModel = result.hourlyUiModel
                )
            }
        }
    }

    private val intentActionMapper =
            Function<WeatherIntent, WeatherAction> { intent ->
                when (intent) {
                    is WeatherIntent.Initial ->
                        WeatherAction.Initial
                }
            }
    
    private val statesObservable: Observable<WeatherState> = compose()
    private fun compose(): Observable<WeatherState> =
            intentsSubject
                    .compose(intentFilter)
                    .map(intentActionMapper)
                    .compose(processor.actionProcessor)
                    .scan(WeatherState.idle(), reducer)
                    .doOnNext { Timber.d("new state created") }
                    .distinctUntilChanged()
                    .replay(1)
                    .autoConnect(0)

}