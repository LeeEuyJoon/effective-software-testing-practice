plugins {
    id("java")
    id("jacoco")
}

group = "study"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")

    // https://jqwik.net/docs/current/user-guide.html#how-to-use
//    testImplementation("net.jqwik:jqwik-api:1.9.3") -> api만 들어있는 모델이라 컴파일만 가능하고 실행은 불가
    testImplementation("net.jqwik:jqwik:1.9.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}