import modularization.applicationGradle
import modularization.kotlinAndroidGradleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationConvention : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applicationGradle {
                kotlinAndroidGradleExtension(this)
                defaultConfig.targetSdk = 34
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }
    }

}
