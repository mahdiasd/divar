plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
    id("convention.android.room")
    id("androidx.room") version ("2.6.1")
}

android {
    namespace = "com.divar.database"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(project(":utils"))
}