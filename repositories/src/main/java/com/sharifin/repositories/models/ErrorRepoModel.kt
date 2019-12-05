package com.sharifin.repositories.models

sealed class ErrorRepoModel {
    class Message(val message: String) : ErrorRepoModel()
    class StringRes(@androidx.annotation.StringRes val stringID: Int) : ErrorRepoModel()
    class UnAuthorized(val errorMessage: String) : ErrorRepoModel()
}