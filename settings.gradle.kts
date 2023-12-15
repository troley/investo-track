pluginManagement {
    plugins {
        java
        id("org.springframework.boot") version "3.2.0"
        id("io.spring.dependency-management") version "1.1.4"
    }
    repositories {
        mavenCentral()
    }
}

rootProject.name = "investo-track"
include("webapp")
