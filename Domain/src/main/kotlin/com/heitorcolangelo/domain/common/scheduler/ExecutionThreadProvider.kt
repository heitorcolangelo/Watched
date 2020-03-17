package com.heitorcolangelo.domain.common.scheduler

import io.reactivex.Scheduler

interface ExecutionThreadProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
