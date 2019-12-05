package com.example.signupuser.viewmodels

import com.example.signupuser.action.ProfileAction
import com.example.signupuser.intent.ProfileIntent
import com.example.signupuser.result.ProfileResult
import com.example.signupuser.state.ProfileState
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


open class ProfileViewModel @Inject constructor(
    private val processor: MviProcessor<ProfileAction, ProfileResult>
) : BaseViewModel<ProfileIntent, ProfileState>() {

    private val intentFilter =
        ObservableTransformer<ProfileIntent, ProfileIntent> { intents ->
            intents.publish { share ->
                Observable.merge(
                    share.ofType(
                        ProfileIntent.InitialIntent::class.java
                    ).take(1),
                    share.notOfType(
                        ProfileIntent.InitialIntent::class.java
                    )
                )
            }
        }

    private val intentsSubject: PublishSubject<ProfileIntent> = PublishSubject.create()
    override fun processIntents(intents: Observable<ProfileIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<ProfileState> = statesObservable


    private val reducer = BiFunction<ProfileState, ProfileResult, ProfileState> { preState, result ->
        when (result) {
            is ProfileResult.LastStable -> {
                preState.copy(
                    base = BaseState.stable(),
                    registerUser = false
                )
            }
            is ProfileResult.Error -> {
                preState.copy(
                    base = result.err.getErrorState(),
                    registerUser = false
                )
            }
            is ProfileResult.Loading -> {
                preState.copy(
                    base = BaseState.loading(),
                    registerUser = false
                )
            }
            is ProfileResult.RegisterUser -> {
                preState.copy(
                    registerUser = true
                )
            }
            is ProfileResult.MaleClick -> {
                preState.copy(
                    registerUser = false,
                    maleClick = true,
                    femaleClick = false
                )
            }
            is ProfileResult.FemaleClick -> {
                preState.copy(
                    registerUser = false,
                    maleClick = false,
                    femaleClick = true
                )
            }
        }
    }

    private val intentActionMapper =
        Function<ProfileIntent, ProfileAction> { intent ->
            when (intent) {
                is ProfileIntent.InitialIntent ->
                    ProfileAction.InitialAction
                is ProfileIntent.MaleClick ->
                    ProfileAction.MaleClick
                is ProfileIntent.FemaleClick ->
                    ProfileAction.FemaleClick
                is ProfileIntent.Confirm ->
                    ProfileAction.Confirm(
                        intent.profileParamsModel
                    )
            }
        }


    private val statesObservable: Observable<ProfileState> = compose()
    private fun compose(): Observable<ProfileState> =
        intentsSubject
            .compose(intentFilter)
            .map(intentActionMapper)
            .compose(processor.actionProcessor)
            .scan(ProfileState.idle(), reducer)
            .doOnNext { Timber.d("new state created") }
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)




}