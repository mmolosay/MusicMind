import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("mtheory.multiplatform.kotlin")
    id("mtheory.multiplatform.compose")
}

group = "io.github.mmolosay"

kotlin {
    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MusicMind"
            packageVersion = "1.0.0"
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":shared"))
}
