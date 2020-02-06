plugins {
    id("kotlin")
    id("java-library")
}

dependencies {
    implementation(DomainDependencies.rxJava)

    testImplementation(DomainDependencies.Test.mockk)
    testImplementation(DomainDependencies.Test.jUnit)
    implementation(kotlin("stdlib-jdk8"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}