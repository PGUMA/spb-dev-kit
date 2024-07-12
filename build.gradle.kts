buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.15.0")
    }
}

plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"

    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.kotlin.jvm)

    id("org.flywaydb.flyway") version "10.15.2"

    id("org.openapi.generator") version "7.7.0"
}

group = "lab.pguma.spb-dev-kit"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.15.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

sourceSets {
    main {
        kotlin {
            srcDir("${openApiGenerate.outputDir.get()}/src/main/kotlin")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

flyway {
    url = "jdbc:postgresql://localhost:5432/dev"
    user = "postgres"
    password = "password"
    cleanDisabled = false // flyway 9
}

// https://openapi-generator.tech/docs/generators/spring/
openApiGenerate {
    generatorName = "spring"
    inputSpec = "$rootDir/specs/petstore.yaml"
    outputDir = layout.buildDirectory.dir("generated").get().asFile.path
    apiPackage = "lab.pguma.spb-dev-kit.api"
    modelPackage = "lab.pguma.spb-dev-kit.model"
    generateModelTests = false
    generateApiTests = false
    generateModelDocumentation = false
    generateApiDocumentation = false
    // https://openapi-generator.tech/docs/generators/spring
    configOptions = mapOf(
        "documentationProvider" to "springdoc",
        "interfaceOnly" to "true",
        "skipDefaultInterface" to "true",
        "useSpringBoot3" to "true"
    )
}