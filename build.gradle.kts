plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	jacoco
}

group = "pl.example"
version = "0.0.1-SNAPSHOT"
val mockitoAgent = configurations.create("mockitoAgent")

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.micrometer:micrometer-core")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains:annotations:24.0.0")
	implementation("org.mapstruct:mapstruct:1.6.3")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")
	compileOnly("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testRuntimeOnly("com.h2database:h2:2.3.232")
	annotationProcessor("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-core:5.15.2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	mockitoAgent("org.mockito:mockito-core:5.15.2") { isTransitive = false }
}

tasks.withType<Test> {
	jvmArgs("-javaagent:${mockitoAgent.asPath}")
	useJUnitPlatform()
}


tasks.test {
	finalizedBy(tasks.jacocoTestReport) // Generate report after tests
	finalizedBy(tasks.jacocoTestCoverageVerification) // Enforce coverage rules
}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // Tests must be run before generating the report

	reports {
		csv.required.set(false)
		html.required.set(true)
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = "0.10".toBigDecimal() // 80% line coverage
			}
		}
	}
}

jacoco {
	toolVersion = "0.8.10"
}