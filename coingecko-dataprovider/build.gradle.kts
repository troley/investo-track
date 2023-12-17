plugins {
	`java-library`
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

sourceSets {
	create("intTest") {
		compileClasspath += sourceSets.main.get().output
		runtimeClasspath += sourceSets.main.get().output
	}
}

val intTestImplementation: Configuration by configurations.getting {
	extendsFrom(configurations.testImplementation.get())
}
val intTestRuntimeOnly: Configuration by configurations.getting

configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.0")
	intTestImplementation("com.squareup.okhttp3:okhttp:4.12.0")
	intTestImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
}
