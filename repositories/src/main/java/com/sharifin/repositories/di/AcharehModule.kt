package com.sharifin.repositories.di

import com.sharifin.core.Mapper
import com.sharifin.core.create
import com.sharifin.repositories.achareh.RegisterUserRepo
import com.sharifin.repositories.achareh.RegisterUserRepoImpl
import com.sharifin.repositories.achareh.UsersListRepo
import com.sharifin.repositories.achareh.UsersListRepoImpl
import com.sharifin.repositories.achareh.mapper.RegisterParamRepoMapper
import com.sharifin.repositories.achareh.mapper.RegisterResponserMapper
import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import com.sharifin.repositories.achareh.repomodel.RegisterParamRepoModel
import com.sharifin.repositories.fetcher.achareh.AcharehApi
import com.sharifin.repositories.fetcher.achareh.FetcherRegisterProfile
import com.sharifin.repositories.fetcher.achareh.FetcherUsersList
import com.sharifin.repository.Fetcher
import com.sharifin.servermodel.AcharehRegisterServerModel
import com.sharifin.servermodel.requestmodels.AcharehRegisterRequestModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Response
import retrofit2.Retrofit

@Module
abstract class AcharehModule {
    @Binds
    abstract fun bindFetcherRegisterProfile(
        fetcher: FetcherRegisterProfile
    ): Fetcher<
            AcharehRegisterRequestModel,
            Response<AcharehRegisterServerModel>
            >

    @Binds
    abstract fun bindFetcherUsersList(
        fetcher: FetcherUsersList
    ): Fetcher<
            Unit,
            Response<ArrayList<AcharehRegisterServerModel>>
            >

    @Binds
    abstract fun bindRegisterResponserMapper(
        mapper: RegisterResponserMapper
    ): Mapper<
            AcharehRegisterServerModel,
            RegisterNewUserRepoModel
            >

    @Binds
    abstract fun bindRegisterParamRepoMapper(
        mapper: RegisterParamRepoMapper
    ): Mapper<
            RegisterParamRepoModel,
            AcharehRegisterRequestModel
            >

    @Binds
    abstract fun bindRegisterUserRepoImpl(
        mapper: RegisterUserRepoImpl
    ): RegisterUserRepo

    @Binds
    abstract fun bindUsersListRepoImpl(
        mapper: UsersListRepoImpl
    ): UsersListRepo

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AcharehApi =
            retrofit.create()
    }
}