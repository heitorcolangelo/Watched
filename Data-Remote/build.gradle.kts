plugins {
    id("kotlin")
    kotlin("kapt")
}

apply {
    from("$rootDir/tools/ktlint.gradle.kts")
}

dependencies {
    implementation(project(DataRemoteDependencies.data))
    implementation(DataRemoteDependencies.dagger)
    kapt(DataRemoteDependencies.daggerCompiler)
    implementation(DataRemoteDependencies.kotlin)
    implementation(DataRemoteDependencies.kotlinReflect)
    implementation(DataRemoteDependencies.moshi)
    implementation(DataRemoteDependencies.okHttp)
    implementation(DataRemoteDependencies.okHttpLogging)
    implementation(DataRemoteDependencies.retrofit)
    implementation(DataRemoteDependencies.retrofitMoshiConverter)
    implementation(DataRemoteDependencies.retrofitRxAdapter)
    implementation(DataRemoteDependencies.rxJava)

    testImplementation(DataRemoteDependencies.Test.jUnit)
    testImplementation(DataRemoteDependencies.Test.mockk)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
