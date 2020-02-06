package com.heitorcolangelo.domain.scheduler

import io.reactivex.Scheduler

interface ObservableProvider {
    val scheduler: Scheduler
}