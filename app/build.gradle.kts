plugins {
    id("convention.android.application")
    id("convention.android.hilt")
    id("convention.android.application.compose")
}

android {
    namespace = "com.divar.application"

    defaultConfig {
        applicationId = "com.divar.application"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BaseUrl", properties["BaseUrl"].toString())
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:secure-shared-pref"))
    implementation(project(":core:database"))
    implementation(project(":core:utils"))

    implementation(project(":feature:category"))
    implementation(project(":feature:home"))
    implementation(project(":feature:location"))
    implementation(project(":feature:splash"))

    implementation(libs.androidx.core.splashscreen)

}