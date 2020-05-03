package com.heitorcolangelo.presentation.common.provider

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ApplicationThreadProvider @Inject constructor() : ExecutionThreadProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
