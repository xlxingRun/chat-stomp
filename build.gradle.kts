plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.xiaolin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // springboot web
    implementation("org.springframework.boot:spring-boot-starter-web")
    // mongoDB
//    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    // MySQL + Jpa
    implementation("mysql:mysql-connector-java:8.0.31")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // WebSocket
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    // WebSocket 集成Spring Security相关
    implementation("org.springframework.security:spring-security-messaging:6.0.2")

    // 热部署
    implementation("org.springframework.boot:spring-boot-devtools:3.0.2")
    // WebSocket API
    compileOnly("jakarta.websocket:jakarta.websocket-api:2.0.0")

    // lombok 注解扩展
     compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok")



    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
