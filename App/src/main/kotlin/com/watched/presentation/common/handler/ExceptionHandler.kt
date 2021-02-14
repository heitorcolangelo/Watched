package com.watched.presentation.common.handler

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

interface ExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler
}