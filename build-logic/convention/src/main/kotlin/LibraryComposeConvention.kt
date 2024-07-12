import modularization.composeGradleExtension
import modularization.libraryGradle
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryComposeConvention : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            applyPlugins()
            libraryGradle {
                composeGradleExtension(this)
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
        }
    }

}