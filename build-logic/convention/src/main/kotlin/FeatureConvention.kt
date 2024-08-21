import modularization.libraryGradle
import modularization.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            applyDependencies()
            libraryGradle {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply(libs.findPlugin("compose.compiler").get().get().pluginId)
            apply("convention.android.library")
            apply("convention.android.library.compose")
            apply("convention.android.hilt")
            apply("convention.android.serialization")
            apply("com.google.devtools.ksp")
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            add("implementation", project(":core:utils"))
            add("implementation", project(":core:ui"))
        }
    }
}