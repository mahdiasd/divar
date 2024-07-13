plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
}

android {
    namespace = "com.divar.secure_shared_pref"
}

dependencies {
    /* --- Encrypt Shared Preferences */
    implementation(libs.androidx.security.crypto)
}