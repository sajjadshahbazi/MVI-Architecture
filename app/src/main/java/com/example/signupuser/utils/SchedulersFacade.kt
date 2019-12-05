package com.example.signupuser.utils

import io.reactivex.Scheduler

interface SchedulersFacade {
    /**
     * IO thread pool scheduler
     */
    fun io(): Scheduler

    /**
     * Computation thread pool scheduler
     */
    fun computation(): Scheduler

    fun single(): Scheduler

    /**
     * Main Thread scheduler
     */
    fun ui(): Scheduler
}