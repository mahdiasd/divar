plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
}

android {
    namespace = "com.divar.domain"

}

dependencies {

    testImplementation(libs.junit.jupiter)
}