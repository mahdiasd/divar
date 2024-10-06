plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
}

android {
    namespace = "com.divar.network"
}

dependencies {
    implementation(project(":core:secure-shared-pref"))

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
}