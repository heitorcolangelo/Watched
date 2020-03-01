plugins {
    id("kotlin")
    kotlin("kapt")
}

apply {
    from("$rootDir/tools/ktlint.gradle.kts")
}

dependencies {
    implementation(project(DataDependencies.domain))
    implementation(DataDependencies.dagger)
    kapt(DataDependencies.daggerCompiler)
    implementation(DataDependencies.kotlin)
    implementation(DataDependencies.kotlinReflect)
    implementation(DataDependencies.rxJava)

    testImplementation(DataDependencies.Test.jUnit)
    testImplementation(DataDependencies.Test.mockk)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
