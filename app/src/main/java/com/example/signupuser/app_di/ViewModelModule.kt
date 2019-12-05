package com.example.signupuser.app_di

import androidx.lifecycle.ViewModelProvider
import com.example.signupuser.di.ListViewModelModulesProvider
import com.example.signupuser.di.MapViewModelModulesProvider
import com.example.signupuser.di.ProfileViewModelModulesProvider
import com.example.signupuser.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [
    ProfileViewModelModulesProvider::class,
    MapViewModelModulesProvider::class,
    ListViewModelModulesProvider::class
])
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}