package com.example.signupuser

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.*
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDex
import com.example.signupuser.BuildConfig.DEBUG
import com.example.signupuser.app_di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@SuppressLint("Registered")
open class App : Application(),
    HasActivityInjector,
    HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private val compositeDisposable = CompositeDisposable()


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        initMultiDex()
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().create(this).inject(this)
    }

    override fun onTerminate() {
        compositeDisposable.clear()
        super.onTerminate()
    }

    private fun initMultiDex() {
        if (DEBUG)
            MultiDex.install(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return supportFragmentInjector
    }

}