@file:Suppress("SpellCheckingInspection", "KDocUnresolvedReference")
plugins {
    id("java-library")
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

/**
 * This method is copied from [KaptConfigurationAccessors.kt]. It looks like kts don't recognize
 * the original method in a pure kotlin module.
 */
fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)