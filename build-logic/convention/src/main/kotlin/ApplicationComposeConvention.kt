import modularization.applicationGradle
import modularization.composeGradleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class ApplicationComposeConvention : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            applyPlugins()
            applicationGradle {
                composeGradleExtension(this)
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
        }
    }

}