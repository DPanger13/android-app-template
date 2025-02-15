plugins {
    id("library-android-compose")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.android.features.featureone"

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(project(":ui:components"))
    implementation(project(":ui:themes"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.viewmodel)

    implementation(libs.bundles.navigation)

    testImplementation(libs.bundles.testing)
}
