package com.example.signupuser.di

import com.example.signupuser.view.ListActivity
import com.example.signupuser.view.ProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListViewBuilder{
    @ContributesAndroidInjector(modules = [
        ListActivityAbstarctModule::class
    ])
    internal abstract fun provideListActivity(): ListActivity
}