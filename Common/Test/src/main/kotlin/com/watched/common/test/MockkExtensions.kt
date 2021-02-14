package com.watched.common.test

import io.mockk.MockK
import io.mockk.MockKDsl
import kotlin.reflect.KClass

/**
 * Same as mockk(), but relaxed.
 */
inline fun <reified T : Any> relaxedMockk(
    name: String? = null,
    vararg moreInterfaces: KClass<*>,
    relaxUnitFun: Boolean = false,
    block: T.() -> Unit = {}
): T = MockK.useImpl {
    MockKDsl.internalMockk(
        name = name,
        relaxed = true,
        *moreInterfaces,
        relaxUnitFun = relaxUnitFun,
        block = block
    )
}