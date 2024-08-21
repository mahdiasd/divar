plugins {
    id("convention.android.feature")
}

android {
    namespace = "com.divar.category"
}

dependencies {
    implementation(project(":domain"))
}