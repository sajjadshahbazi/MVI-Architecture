package com.example.signupuser.app_di

import com.example.signupuser.di.ListViewBuilder
import com.example.signupuser.di.MapViewBuilder
import com.example.signupuser.di.ProfileViewBuilder
import dagger.Module

@Module(includes = [
    ProfileViewBuilder::class,
    MapViewBuilder::class,
    ListViewBuilder::class
])
abstract class BuildersModule