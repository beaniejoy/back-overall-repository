import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
//import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"

    id("org.jlleitschuh.gradle.ktlint").version("12.1.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    group = "io.beaniejoy"

    repositories {
        mavenCentral()
    }

    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    ktlint {
        reporters {
            reporter(ReporterType.JSON)
        }
    }

    // report directory location setting
//    tasks.withType<GenerateReportsTask> {
//        reportsOutputDirectory.set(
//            rootProject.layout.buildDirectory.dir("reports/ktlint/${project.name}")
//        )
//    }
}

subprojects {


    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")

        plugin("kotlin")
        plugin("kotlin-spring")
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}




