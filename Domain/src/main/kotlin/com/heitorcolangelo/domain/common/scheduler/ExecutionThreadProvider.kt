package com.heitorcolangelo.domain.common.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface ExecutionThreadProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
