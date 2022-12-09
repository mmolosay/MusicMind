import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class MultiplatformComposePlugin : Plugin<Project> {

    override fun apply(target: Project) =
        target.run {
            configurePlugins()
            configureKotlin()
            configureKotlinCompiler()
        }

    private fun Project.configurePlugins() =
        pluginManager.run {
            apply("kotlin-multiplatform")
            apply("org.jetbrains.compose")
        }

    private fun Project.configureKotlin() =
        kotlin {
            jvmDefaultConfig()
            projectSourceSets()
        }

    private fun KotlinMultiplatformExtension.projectSourceSets() =
        sourceSets {
            commonMain()
//            desktopMain()
        }

    @OptIn(ExperimentalComposeLibrary::class)
    private fun NamedDomainObjectContainer<KotlinSourceSet>.commonMain() =
        named("commonMain") {
            dependencies {
                implementation(ComposePlugin.Dependencies.runtime)
                implementation(ComposePlugin.Dependencies.foundation)
                implementation(ComposePlugin.Dependencies.material3)
            }
        }

//    private fun NamedDomainObjectContainer<KotlinSourceSet>.desktopMain() =
//        named("desktopMain") {
//            dependencies {
//                implementation(ComposePlugin.Dependencies.desktop.common)
//            }
//        }
}