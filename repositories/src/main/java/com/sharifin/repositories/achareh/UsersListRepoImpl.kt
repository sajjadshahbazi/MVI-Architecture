package com.sharifin.repositories.achareh

import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import com.sharifin.repository.Fetcher
import com.sharifin.retrofit.ErrorHolder
import com.sharifin.retrofit.isNotSuccessful
import com.sharifin.retrofit.retrofitutils.getErrorHolder
import com.sharifin.servermodel.AcharehRegisterServerModel
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class UsersListRepoImpl @Inject constructor(

    private val fetcherList : Fetcher<
            Unit,
            Response<ArrayList<AcharehRegisterServerModel>>
            >,
    private val resMapper: Mapper<
            AcharehRegisterServerModel,
            RegisterNewUserRepoModel
            >
) : UsersListRepo {


    override fun getList(): Single<ListRepoOutputModel> =
        fetcherList.fetch(Unit)
            .map {
                if (it.isNotSuccessful) {
                    return@map ListRepoOutputModel.Error(it.getErrorHolder())
                }
                val body = it.body()
                    ?: return@map ListRepoOutputModel.Error(
                        ErrorHolder.Message(
                            "خطای سرور"
                        )
                    )
                ListRepoOutputModel.Success(
                    body.map{resMapper.map(it)}
                )
            }

}