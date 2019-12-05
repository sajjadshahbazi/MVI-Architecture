package com.example.signupuser.app_di

import com.example.signupuser.network.BASE_URL
import com.example.signupuser.utils.SchedulersFacade
import com.sharifin.servermodel.ApplicationJsonAdapterFactory

import com.sharifin.servermodel.ArrayListMoshiAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpModule::class])
object NetworkModule {

    @JvmStatic
    @Provides
    fun provideTypeAdapter(): Moshi {
        val builder = Moshi.Builder()
        builder.add(ApplicationJsonAdapterFactory.INSTANCE)
        builder.add(ArrayListMoshiAdapter.FACTORY)
        return builder.build()
    }


    @JvmStatic
    @Singleton
    @Provides
    fun provideRestHelper(client: OkHttpClient, moshi: Moshi, scheduler: SchedulersFacade): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler.io()))
            .build()
    }

}
