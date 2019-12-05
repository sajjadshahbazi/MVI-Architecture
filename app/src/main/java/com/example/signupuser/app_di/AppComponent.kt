package com.example.signupuser.app_di



import com.example.signupuser.App
import com.sharifin.repositories.di.RepositoriesModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
//import CacheModule

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BuildersModule::class,
    ViewModelModule::class,
    RepositoriesModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        abstract override fun build(): AppComponent
    }

    override fun inject(app: App)
}