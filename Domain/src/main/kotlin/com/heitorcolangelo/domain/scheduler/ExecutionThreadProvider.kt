package com.heitorcolangelo.domain.scheduler

import io.reactivex.Scheduler

interface ExecutionThreadProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
