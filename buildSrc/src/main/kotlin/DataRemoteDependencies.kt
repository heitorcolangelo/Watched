object DataRemoteDependencies {
    const val dagger = CommonDependencies.dagger
    const val daggerCompiler = CommonDependencies.daggerCompiler
    const val data = ":Data"
    const val kotlin = CommonDependencies.kotlin
    const val kotlinReflect = CommonDependencies.kotlinReflect
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val rxJava = CommonDependencies.rxJava

    object Test {
        const val jUnit = CommonDependencies.jUnit
        const val mockk = CommonDependencies.mockk
    }
}