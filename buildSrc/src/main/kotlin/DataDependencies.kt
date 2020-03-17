object DataDependencies {
    const val dagger = CommonDependencies.dagger
    const val daggerCompiler = CommonDependencies.daggerCompiler
    const val domain = ":Domain"
    const val kotlin = CommonDependencies.kotlin
    const val kotlinReflect = CommonDependencies.kotlinReflect
    const val rxJava = CommonDependencies.rxJava

    object Test {
        const val jUnit = CommonDependencies.Test.jUnit
        const val mockk = CommonDependencies.Test.mockk
    }
}