pluginManagement {
    plugins {
        java
        id("org.springframework.boot") version "3.2.0"
        id("io.spring.dependency-management") version "1.1.4"
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

// This ia a settings plugin and must be applied in the settings file.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "investo-track"
include("webapp")
include("coingecko-dataprovider")
include("dataprovider-core")
