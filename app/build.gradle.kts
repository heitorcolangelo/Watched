plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

apply {
    from("$rootDir/tools/ktlint.gradle.kts")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools
    defaultConfig {
        applicationId = "com.heitorcolangelo.skeleton"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(AppDependencies.appCompat)
    implementation(AppDependencies.coreKtx)
    implementation(AppDependencies.koinAndroid)
    implementation(AppDependencies.koinScope)
    implementation(AppDependencies.koinViewModel)
    implementation(AppDependencies.kotlin)

    testImplementation(AppDependencies.Test.jUnit)
    testImplementation(AppDependencies.Test.mockk)

    androidTestImplementation(AppDependencies.AndroidTest.jUnitExtension)
    androidTestImplementation(AppDependencies.AndroidTest.espressoCore)
}
