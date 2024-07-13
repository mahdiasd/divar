plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)

}

gradlePlugin {
    plugins {
        register("androidApplicationCompose")
        {
            id = "convention.android.application.compose"
            implementationClass = "ApplicationComposeConvention"
        }

        register("androidLibraryCompose")
        {
            id = "convention.android.library.compose"
            implementationClass = "LibraryComposeConvention"
        }

        register("application")
        {
            id = "convention.android.application"
            implementationClass = "ApplicationConvention"
        }

        register("applicationLibrary")
        {
            id = "convention.android.library"
            implementationClass = "LibraryConvention"
        }

        register("androidHilt") {
            id = "convention.android.hilt"
            implementationClass = "HiltConvention"
        }

        register("androidSerialization") {
            id = "convention.android.serialization"
            implementationClass = "SerializationConvention"
        }

        register("androidFeature") {
            id = "convention.android.feature"
            implementationClass = "FeatureConvention"
        }

        register("room") {
            id = "convention.android.room"
            implementationClass = "RoomConvention"
        }

    }
}