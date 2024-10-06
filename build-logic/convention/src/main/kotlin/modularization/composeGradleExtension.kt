package modularization

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.composeGradleExtension(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {

            val bom = libs.findLibrary("androidx.compose.bom").get()
            add("implementation", platform(bom))

            add("implementation", libs.findLibrary("androidx.activity.compose").get())
            add("implementation", libs.findLibrary("androidx.ui").get())
            add("implementation", libs.findLibrary("androidx.ui.graphics").get())

            add("implementation", libs.findLibrary("androidx.ui.tooling").get())
            add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
            add("implementation", libs.findLibrary("androidx.material3").get())
            add("implementation", libs.findLibrary("coil.compose").get())
            add("implementation", libs.findLibrary("constraintlayout.compose").get())
            add("implementation", libs.findLibrary("accompanist.swiperefresh").get())
            add("implementation", libs.findLibrary("coil.svg").get())
            add("implementation", libs.findLibrary("androidx.material.icons.extended").get())

            add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.tooling.preview").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
        }

    }

}