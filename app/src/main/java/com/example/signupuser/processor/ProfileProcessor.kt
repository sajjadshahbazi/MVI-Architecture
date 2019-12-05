package com.example.signupuser.processor

import com.example.signupuser.action.ProfileAction
import com.example.signupuser.model.Female
import com.example.signupuser.model.Male
import com.example.signupuser.model.ProfileParamsModel
import com.example.signupuser.result.ProfileResult
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.Mapper
import com.sharifin.core.defaultClicksLastStableResultWithStart
import com.sharifin.core.defaultLastStableResultWithStart
import com.sharifin.repositories.achareh.RegisterUserRepo
import com.sharifin.repositories.achareh.RegisterUserRepoOutputModel
import com.sharifin.repositories.achareh.repomodel.RegisterParamRepoModel
import com.sharifin.retrofit.ErrorHolder
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProfileProcessor @Inject constructor(
    private val registerUserRepo: RegisterUserRepo,
    paramMapper: Mapper<
            ProfileParamsModel,
            RegisterParamRepoModel
            >
) : MviProcessor<ProfileAction, ProfileResult> {
    override val actionProcessor: ObservableTransformer<ProfileAction, ProfileResult> =
        ObservableTransformer { actions ->
            actions.publish { publish ->
                Observable.merge(
                    listOf(
                        publish.ofType(ProfileAction.InitialAction::class.java)
                            .compose(initial),
                        publish.ofType(ProfileAction.Confirm::class.java)
                            .compose(registerUser),
                        publish.ofType(ProfileAction.MaleClick::class.java)
                            .compose(maleClick),
                        publish.ofType(ProfileAction.FemaleClick::class.java)
                            .compose(femaleClick)
                    )
                ).observeOn(AndroidSchedulers.mainThread())
            }
        }


    private val initial =
        ObservableTransformer<ProfileAction.InitialAction, ProfileResult> { actions ->
            actions.switchMap {
                Observable.just(ProfileResult.LastStable)
            }
        }

    private val maleClick =
        ObservableTransformer<ProfileAction.MaleClick, ProfileResult> { actions ->
            actions.switchMap {
                Observable.just(ProfileResult.MaleClick)
            }
        }

    private val femaleClick =
        ObservableTransformer<ProfileAction.FemaleClick, ProfileResult> { actions ->
            actions.switchMap {
                Observable.just(ProfileResult.FemaleClick)
            }
        }

    private val registerUser =
        ObservableTransformer<ProfileAction.Confirm, ProfileResult> { actions ->
            actions.switchMap { action ->
                if(action.profileParamsModel.address.isNotEmpty() &&
                    action.profileParamsModel.firstName.isNotEmpty() &&
                    action.profileParamsModel.lastName.isNotEmpty() &&
                    action.profileParamsModel.gender.isNotEmpty() &&
                    (action.profileParamsModel.gender == Male || action.profileParamsModel.gender == Female) &&
                    action.profileParamsModel.coordinateMobile.length == 11 &&
                    action.profileParamsModel.coordinatePhoneNumber.length == 11) {
                    registerUserRepo.register(paramMapper.map(action.profileParamsModel))
                        .toObservable()
                        .switchMap {
                            when (it) {
                                is RegisterUserRepoOutputModel.Success -> {
                                    defaultClicksLastStableResultWithStart(
                                        ProfileResult.LastStable,
                                        ProfileResult.RegisterUser
                                    )
                                }
                                is RegisterUserRepoOutputModel.Error -> {
                                    defaultLastStableResultWithStart(
                                        ProfileResult.LastStable,
                                        ProfileResult.Error(it.err)
                                    ).observeOn(AndroidSchedulers.mainThread())
                                }
                                else ->
                                    throw IllegalArgumentException("this output=$it is not expected here.")
                            }
                        }.startWith(ProfileResult.Loading)
                }else{
                    defaultLastStableResultWithStart(
                        ProfileResult.LastStable,
                        ProfileResult.Error(ErrorHolder.Message("لطفا پارامتر ها را با دقت تکمیل کنید."))
                    ).observeOn(AndroidSchedulers.mainThread())
                }
            }
        }
}