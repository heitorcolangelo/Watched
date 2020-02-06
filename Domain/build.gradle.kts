plugins {
    kotlin("jvm")
}

dependencies {
    implementation(DomainDependencies.rxJava)

    testImplementation(DomainDependencies.Test.mockk)
    testImplementation(DomainDependencies.Test.jUnit)
}