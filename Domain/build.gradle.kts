plugins {
    id("kotlin")
    id("java-library")
}

apply {
    from("$rootDir/tools/ktlint.gradle.kts")
}

dependencies {
    implementation(DomainDependencies.rxJava)
    implementation(DomainDependencies.kotlin)

    testImplementation(DomainDependencies.Test.mockk)
    testImplementation(DomainDependencies.Test.jUnit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}