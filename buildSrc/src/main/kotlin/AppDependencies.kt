object AppDependencies {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinScope = "org.koin:koin-android-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    const val kotlin = CommonDependencies.kotlin

    object Test {
        const val jUnit = CommonDependencies.jUnit
        const val mockk = CommonDependencies.mockk
    }

    object AndroidTest {
        const val jUnitExtension = "androidx.test.ext:junit:${Versions.AndroidX.jUnitExtension}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.AndroidX.espressoCore}"
    }
}