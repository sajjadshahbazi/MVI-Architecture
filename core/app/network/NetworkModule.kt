package com.sharifin.mbank.app.network

import com.sharifin.base.qualifiers.GetRetrofitForPoliceYar
import com.sharifin.mbank.rxutils.SchedulersFacade
import com.sharifin.mbank.servermodel.ApplicationJsonAdapterFactory
import com.sharifin.mbank.servermodel.ArrayListMoshiAdapter
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

    @JvmStatic
    @Singleton
    @Provides
    @GetRetrofitForPoliceYar
    fun provideRestHelperForPoliceYar(client: OkHttpClient, moshi: Moshi, scheduler: SchedulersFacade): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_Police_Yar)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler.io()))
                .build()
    }
}
