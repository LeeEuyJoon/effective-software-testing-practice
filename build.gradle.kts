plugins {
    id("java")
}

group = "study"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://jqwik.net/docs/current/user-guide.html#how-to-use
    testImplementation("net.jqwik:jqwik-api:1.9.3")
}

tasks.test {
    useJUnitPlatform()
}