package com.example.signupuser.di

import androidx.lifecycle.ViewModel
import com.example.signupuser.action.ListAction
import com.example.signupuser.mapper.UserRepoUiMapper
import com.example.signupuser.model.UserUiModel
import com.example.signupuser.processor.ListProcessor
import com.example.signupuser.result.ListResult
import com.example.signupuser.viewmodels.ListViewModel
import com.sharifin.base.ViewModelKey
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterNewUserRepoModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    internal abstract fun postListViewModel(
        viewModel: ListViewModel
    ): ViewModel

    @Binds
    internal abstract fun postListProcessor(
        processor: ListProcessor
    ): MviProcessor<
            ListAction,
            ListResult
            >

    @Binds
    internal abstract fun postUserRepoUiMapper(
        mapper: UserRepoUiMapper
    ): Mapper<
            RegisterNewUserRepoModel,
            UserUiModel
            >
}