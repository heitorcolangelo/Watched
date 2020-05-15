package com.heitorcolangelo.test.common.presentation

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler

class TestExecutionThreadProvider : ExecutionThreadProvider {
    override fun io(): Scheduler {
        return TrampolineScheduler.instance()
    }

    override fun ui(): Scheduler {
        return TrampolineScheduler.instance()
    }
}
