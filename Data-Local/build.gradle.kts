plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

apply {
    from("$rootDir/tools/ktlint.gradle.kts")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation(DataLocalDependencies.dagger)
    kapt(DataLocalDependencies.daggerCompiler)
    implementation(project(DataLocalDependencies.data))
    implementation(DataLocalDependencies.kotlin)
    implementation(DataLocalDependencies.kotlinReflect)
    implementation(DataLocalDependencies.roomCompiler)
    implementation(DataLocalDependencies.roomRuntime)
    implementation(DataLocalDependencies.roomRxJava)
    implementation(DataLocalDependencies.rxJava)

    testImplementation(DataLocalDependencies.Test.arch)
    testImplementation(DataLocalDependencies.Test.jUnit)
    testImplementation(DataLocalDependencies.Test.mockk)
    testImplementation(DataLocalDependencies.Test.room)
    testImplementation(DataLocalDependencies.Test.supportRules)
    testImplementation(DataLocalDependencies.Test.supportRunner)
}
