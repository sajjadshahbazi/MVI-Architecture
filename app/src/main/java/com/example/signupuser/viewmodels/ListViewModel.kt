package com.example.signupuser.viewmodels


import com.example.signupuser.action.ListAction
import com.example.signupuser.intent.ListIntent
import com.example.signupuser.result.ListResult
import com.example.signupuser.state.ListState
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

open class ListViewModel @Inject constructor(
    private val processor: MviProcessor<ListAction, ListResult>
) : BaseViewModel<ListIntent, ListState>() {

    private val intentFilter =
        ObservableTransformer<ListIntent, ListIntent> { intents ->
            intents.publish { share ->
                Observable.merge(
                    share.ofType(
                        ListIntent.InitialIntent::class.java
                    ).take(1),
                    share.notOfType(
                        ListIntent.InitialIntent::class.java
                    )
                )
            }
        }

    private val intentsSubject: PublishSubject<ListIntent> = PublishSubject.create()
    override fun processIntents(intents: Observable<ListIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<ListState> = statesObservable


    private val reducer = BiFunction<ListState, ListResult, ListState> { preState, result ->
        when (result) {
            is ListResult.LastStable -> {
                preState.copy(
                    base = BaseState.stable()
                )
            }
            is ListResult.UserList -> {
                preState.copy(
                    userUiModels = result.users
                )
            }
            is ListResult.Error -> {
                preState.copy(
                    base = result.err.getErrorState()
                )
            }
            is ListResult.Loading -> {
                preState.copy(
                    base = BaseState.loading()
                )
            }
        }
    }

    private val intentActionMapper =
        Function<ListIntent, ListAction> { intent ->
            when (intent) {
                is ListIntent.InitialIntent ->
                    ListAction.InitialAction
            }
        }


    private val statesObservable: Observable<ListState> = compose()
    private fun compose(): Observable<ListState> =
        intentsSubject
            .compose(intentFilter)
            .map(intentActionMapper)
            .compose(processor.actionProcessor)
            .scan(ListState.idle(), reducer)
            .doOnNext { Timber.d("new state created") }
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)




}