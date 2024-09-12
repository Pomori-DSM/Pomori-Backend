plugins {
    id("java")
}

group = "com.pomori"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.3")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // MySQL
    implementation("mysql:mysql-connector-java")

    // Lombok
    implementation("org.lombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}