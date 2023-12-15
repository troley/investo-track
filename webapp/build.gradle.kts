plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
