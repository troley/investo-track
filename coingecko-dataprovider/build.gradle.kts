plugins {
	java
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

dependencies {
	implementation(project(":dataprovider-core"))

	implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.0")
	intTestImplementation("com.squareup.okhttp3:okhttp:4.12.0")
	intTestImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
}
