import org.jetbrains.compose.ComposePlugin

plugins {
    id("mtheory.multiplatform.kotlin")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        named("commonMain") {
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            dependencies {
                implementation(ComposePlugin.Dependencies.runtime)
                implementation(ComposePlugin.Dependencies.foundation)
                implementation(ComposePlugin.Dependencies.material3)
            }
        }
//            named("desktopMain") {
//                dependencies {
//                    implementation(ComposePlugin.Dependencies.desktop.common)
//                }
//            }
    }
}