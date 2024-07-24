plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
}

android {
    namespace = "com.divar.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":domain"))
}