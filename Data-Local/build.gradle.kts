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

    sourceSets {
        getByName("main") {
            java.setSrcDirs(mutableListOf("src/main/kotlin"))
        }
        getByName("test") {
            java.setSrcDirs(mutableListOf("src/test/kotlin"))
        }
        getByName("androidTest") {
            java.setSrcDirs(mutableListOf("src/androidTest/kotlin"))
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
    kapt(DataLocalDependencies.roomCompiler)
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
