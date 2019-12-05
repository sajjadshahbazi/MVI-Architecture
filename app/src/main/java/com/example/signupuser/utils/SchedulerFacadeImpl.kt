package com.example.signupuser.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulerFacadeImpl @Inject constructor(): SchedulersFacade {

    override fun io(): Scheduler = Schedulers.io()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun single(): Scheduler = Schedulers.single()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}