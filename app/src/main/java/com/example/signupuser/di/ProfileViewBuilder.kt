package com.example.signupuser.di

import com.example.signupuser.view.ProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileViewBuilder{
    @ContributesAndroidInjector(modules = [
        ProfileActivityAbstarctModule::class
    ])
    internal abstract fun provideProfileActivity(): ProfileActivity
}