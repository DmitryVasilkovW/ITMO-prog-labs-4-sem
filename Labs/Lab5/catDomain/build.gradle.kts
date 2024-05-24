plugins {
    id("java")
}

group = "org.lab5"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation(project(":dataAccess"))
}

tasks.test {
    useJUnitPlatform()
}