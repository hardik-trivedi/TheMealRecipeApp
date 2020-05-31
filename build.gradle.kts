// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlinVersion = "1.3.31"
    val navVersion = "2.1.0"
    val dokkaVersion = "0.10.1"

    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.5.2.0")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://oss.jfrog.org/libs-snapshot")
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
