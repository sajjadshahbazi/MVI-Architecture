package com.example.signupuser.viewmodels

import com.example.signupuser.action.MapAction
import com.example.signupuser.intent.MapIntent
import com.example.signupuser.result.MapResult
import com.example.signupuser.state.MapState
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

open class MapViewModel @Inject constructor(
    private val processor: MviProcessor<MapAction, MapResult>
) : BaseViewModel<MapIntent, MapState>() {

    private val intentFilter =
        ObservableTransformer<MapIntent, MapIntent> { intents ->
            intents.publish { share ->
                Observable.merge(
                    share.ofType(
                        MapIntent.InitialIntent::class.java
                    ).take(1),
                    share.notOfType(
                        MapIntent.InitialIntent::class.java
                    )
                )
            }
        }

    private val intentsSubject: PublishSubject<MapIntent> = PublishSubject.create()
    override fun processIntents(intents: Observable<MapIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<MapState> = statesObservable


    private val reducer = BiFunction<MapState, MapResult, MapState> { preState, result ->
        when (result) {
            is MapResult.LastStable -> {
                preState.copy(
                    base = BaseState.stable(),
                    goToFormView = false
                )
            }
            is MapResult.Confirm -> {
                preState.copy(
                    goToFormView = true,
                    lat = result.lat,
                    lng = result.lng
                )
            }
        }
    }

    private val intentActionMapper =
        Function<MapIntent, MapAction> { intent ->
            when (intent) {
                is MapIntent.InitialIntent ->
                    MapAction.InitialAction
                is MapIntent.ConfirmIntent ->
                    MapAction.ConfirmIntent(
                        lat = intent.lat,
                        lng = intent.lng
                    )
            }
        }


    private val statesObservable: Observable<MapState> = compose()
    private fun compose(): Observable<MapState> =
        intentsSubject
            .compose(intentFilter)
            .map(intentActionMapper)
            .compose(processor.actionProcessor)
            .scan(MapState.idle(), reducer)
            .doOnNext { Timber.d("new state created") }
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)




}