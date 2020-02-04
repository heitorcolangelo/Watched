object AppDependencies {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    object Test {
        const val jUnit = "junit:junit:${Versions.jUnit}"
    }

    object AndroidTest {
        const val jUnitExtension = "androidx.test.ext:junit:${Versions.AndroidX.jUnitExtension}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.AndroidX.espressoCore}"
    }
}