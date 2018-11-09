import groovy.lang.Closure
import groovy.lang.GroovyObject
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion = "2.1.0.RELEASE"
val springCloudVersion by extra { "Finchley.SR2" }

buildscript {
    repositories {
        mavenLocal()
        jcenter()
    }
}

plugins {
    val kotlinVersion = "1.3.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion

    // this works
    // id("org.springframework.boot") version "2.1.0.RELEASE"

    id("org.springframework.boot") version "2.1.0.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

group = "com.beltram"
version = "0.0.1-SNAPSHOT"

apply {
    plugin("kotlin")
    plugin("io.spring.dependency-management")
    plugin("org.jetbrains.kotlin.plugin.spring")
}

// this fails
the<DependencyManagementExtension>().apply {
    imports { mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES) }
}
// fails also
//dependencyManagement {
//    imports {
//        // all 3 fail same way
//        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
//        mavenBom("org.springframework.boot:spring-boot-starter-parent:$springBootVersion")
//        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
//    }
//}

dependencies {
    val kotlinVersion = "1.3.0"
    compile("org.springframework.boot:spring-boot-starter-webflux")
    implementation(kotlin("stdlib", kotlinVersion))
    implementation(kotlin("stdlib-jdk7", kotlinVersion))
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))
    testImplementation("org.springframework.boot:spring-boot-starter-test") { exclude(module = "junit") }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=compatibility")
        }
    }
    withType<Test> { useJUnitPlatform() }
}