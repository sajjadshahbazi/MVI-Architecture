package com.example.signupuser.processor


import com.example.signupuser.action.ListAction
import com.example.signupuser.model.UserUiModel
import com.example.signupuser.result.ListResult
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.Mapper
import com.sharifin.core.defaultClicksLastStableResultWithStart
import com.sharifin.core.defaultLastStableResultWithStart
import com.sharifin.repositories.achareh.ListRepoOutputModel
import com.sharifin.repositories.achareh.UsersListRepo
import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ListProcessor @Inject constructor(
    private val mapper: Mapper<
            RegisterNewUserRepoModel,
            UserUiModel
            >,
    private val repo: UsersListRepo
) : MviProcessor<ListAction, ListResult> {
    override val actionProcessor: ObservableTransformer<ListAction, ListResult> =
        ObservableTransformer { actions ->
            actions.publish { publish ->
                Observable.merge(
                    listOf(
                        publish.ofType(ListAction.InitialAction::class.java)
                            .compose(initial)
                    )
                ).observeOn(AndroidSchedulers.mainThread())
            }
        }

    private val initial =
        ObservableTransformer<ListAction.InitialAction, ListResult> { actions ->
            actions.switchMap { action ->
                repo.getList()
                    .toObservable()
                    .switchMap {
                        when (it) {
                            is ListRepoOutputModel.Success -> {
                                defaultClicksLastStableResultWithStart(
                                    ListResult.LastStable,
                                    ListResult.UserList(it.listUsers.map { mapper.map(it) })
                                )
                            }
                            is ListRepoOutputModel.Error -> {
                                defaultLastStableResultWithStart(
                                    ListResult.LastStable,
                                    ListResult.Error(it.err)
                                )
                            }
                            else ->
                                throw IllegalArgumentException("this output=$it is not expected here.")
                        }
                    }.startWith(ListResult.Loading)
            }
        }
}