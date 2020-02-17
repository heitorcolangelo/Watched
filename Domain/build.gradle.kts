plugins {
    id("kotlin")
    id("java-library")
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