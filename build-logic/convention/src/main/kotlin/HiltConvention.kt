import modularization.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConvention : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("dagger.hilt.android.plugin")
            apply("com.google.devtools.ksp")
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            add("implementation", libs.findLibrary("dagger.hilt.android").get())
            add("implementation", libs.findLibrary("dagger.hilt.navigation").get())
            add("ksp", libs.findLibrary("dagger.hilt.compiler").get())
        }
    }
}