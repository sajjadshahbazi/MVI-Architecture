package com.example.signupuser.app_di

import android.app.Application
import android.content.Context
import com.example.signupuser.App
import com.example.signupuser.utils.SchedulerFacadeImpl
import com.example.signupuser.utils.SchedulersFacade
import com.sharifin.core.Base64ToolsImpl
import com.sharifin.core.Base64Tools
import com.sharifin.navigation.di.NavigationModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module(includes = [
    NetworkModule::class,
    NavigationModule::class,
    NavigatorModule::class
])
abstract class AppModule {
    @Binds
    abstract fun bindContext(app: App): Context

    @Binds
    abstract fun bindApplication(app: App): Application

    @Binds
    abstract fun bindSchedulers(schedulerFacade: SchedulerFacadeImpl): SchedulersFacade

    @Binds
    abstract fun bindBase64ExtensionsImpl(base64ExtensionsImpl: Base64ToolsImpl): Base64Tools

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }
}
