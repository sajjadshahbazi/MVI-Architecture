package com.example.signupuser.di

import androidx.lifecycle.ViewModel
import com.example.signupuser.action.MapAction
import com.example.signupuser.processor.MapProcessor
import com.example.signupuser.result.MapResult
import com.example.signupuser.viewmodels.MapViewModel
import com.sharifin.base.ViewModelKey
import com.sharifin.base.mvibase.MviProcessor
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MapViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    internal abstract fun postProfileViewModel(
        viewModel: MapViewModel
    ): ViewModel

    @Binds
    internal abstract fun postMapProcessor(
        processor: MapProcessor
    ): MviProcessor<MapAction, MapResult>
}