object DataLocalDependencies {
    const val dagger = CommonDependencies.dagger
    const val daggerCompiler = CommonDependencies.daggerCompiler
    const val data = ":Data"
    const val kotlin = CommonDependencies.kotlin
    const val kotlinReflect = CommonDependencies.kotlinReflect
    const val roomCompiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.AndroidX.room}"
    const val roomRxJava = "androidx.room:room-rxjava2:${Versions.AndroidX.room}"
    const val rxJava = CommonDependencies.rxJava

    object Test {
        const val arch = "android.arch.core:core-testing:${Versions.AndroidX.room}"
        const val jUnit = CommonDependencies.Test.jUnit
        const val mockk = CommonDependencies.Test.mockk
        const val room = "android.arch.persistence.room:testing:${Versions.AndroidX.room}"
        const val supportRules = CommonDependencies.Test.supportRules
        const val supportRunner = CommonDependencies.Test.supportRunner
    }
}