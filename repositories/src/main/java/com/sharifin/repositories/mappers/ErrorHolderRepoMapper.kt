package com.sharifin.repositories.mappers

import com.sharifin.core.Mapper
import com.sharifin.repositories.models.ErrorRepoModel
import com.sharifin.retrofit.ErrorHolder
import javax.inject.Inject

class ErrorHolderRepoMapper @Inject constructor(): Mapper<ErrorHolder, ErrorRepoModel> {

    override fun map(item: ErrorHolder): ErrorRepoModel =
            when (item) {
                is ErrorHolder.Message ->
                    ErrorRepoModel.Message(item.message)
                is ErrorHolder.StringRes ->
                    ErrorRepoModel.StringRes(item.stringID)
                is ErrorHolder.UnAuthorized ->
                    ErrorRepoModel.UnAuthorized(item.errorMessage)
            }
}