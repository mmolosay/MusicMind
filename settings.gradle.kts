pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("multiplatform") version extra["kotlinVersion"].toString()
        id("org.jetbrains.compose") version extra["composeVersion"].toString()
    }
}

rootProject.name = "MTheory"
include(":desktop")
