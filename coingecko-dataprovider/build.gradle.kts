plugins {
	`java-library`
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.0")
}
