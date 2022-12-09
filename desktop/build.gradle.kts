import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("mtheory.multiplatform.kotlin")
    id("mtheory.multiplatform.compose")
}

group = "com.mmolosay.mtheory.desktop"

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
            packageName = "MTheory"
            packageVersion = "1.0.0"
        }
    }
}
