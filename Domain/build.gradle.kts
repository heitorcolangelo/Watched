plugins {
    id("kotlin")
    kotlin("kapt")
}

apply {
    from("$rootDir/tools/ktlint.gradle.kts")
}

dependencies {
    implementation(DomainDependencies.dagger)
    kapt(DomainDependencies.daggerCompiler)
    implementation(DomainDependencies.kotlin)
    implementation(DomainDependencies.kotlinReflect)
    implementation(DomainDependencies.rxJava)

    testImplementation(DomainDependencies.Test.jUnit)
    testImplementation(DomainDependencies.Test.mockk)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}