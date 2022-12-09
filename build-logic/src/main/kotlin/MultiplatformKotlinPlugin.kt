import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformKotlinPlugin : Plugin<Project> {

    override fun apply(target: Project) =
        target.run {
            applyPlugins()
            configureKotlin()
            configureKotlinCompiler()
        }

    private fun Project.applyPlugins() =
        pluginManager.run {
            apply("kotlin-multiplatform")
        }

    private fun Project.configureKotlin() =
        kotlin {
            jvmDefaultConfig()
        }
}