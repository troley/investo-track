plugins {
	java
}

allprojects {
	group = "com.example"

	subprojects {
		plugins.withId("java") {
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
		}
	}

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
