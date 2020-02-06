package com.heitorcolangelo.domain.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    val scheduler: Scheduler
}