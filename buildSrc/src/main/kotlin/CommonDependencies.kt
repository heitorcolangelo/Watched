internal object CommonDependencies {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val rxJava = "io.reactivex.rxjava2:rxkotlin:${Versions.rxJava}"

    internal object Test {
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val supportRules = "com.android.support.test:rules:${Versions.supportTestRule}"
        const val supportRunner = "com.android.support.test:runner:${Versions.supportTestRunner}"
    }
}