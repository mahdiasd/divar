plugins {
    id("convention.android.feature")
}
android {
    namespace = "com.divar.splash"
}

dependencies {
    implementation(libs.androidx.core.splashscreen)
}