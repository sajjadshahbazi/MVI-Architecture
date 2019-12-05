package com.example.signupuser.di

import androidx.lifecycle.ViewModel
import com.example.signupuser.action.ProfileAction
import com.example.signupuser.mapper.ProfileParamsRepoMapper
import com.example.signupuser.model.ProfileParamsModel
import com.example.signupuser.processor.ProfileProcessor
import com.example.signupuser.result.ProfileResult
import com.example.signupuser.viewmodels.ProfileViewModel
import com.sharifin.base.ViewModelKey
import com.sharifin.base.mvibase.MviProcessor
import com.sharifin.core.Mapper
import com.sharifin.repositories.achareh.repomodel.RegisterParamRepoModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun postProfileViewModel(
        viewModel: ProfileViewModel
    ): ViewModel

    @Binds
    internal abstract fun postProfileProcessor(
        processor: ProfileProcessor
    ): MviProcessor<ProfileAction, ProfileResult>

    @Binds
    internal abstract fun postProfileParamsRepoMapper(
        mapper : ProfileParamsRepoMapper
    ): Mapper<
            ProfileParamsModel,
            RegisterParamRepoModel
            >
}