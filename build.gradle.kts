plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter:3.2.3")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.3")
    implementation("org.liquibase:liquibase-core:4.26.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.3")
    implementation("org.hibernate:hibernate-core:6.5.0.CR1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
}

tasks.test {
    useJUnitPlatform()
}