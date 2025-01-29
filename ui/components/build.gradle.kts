plugins {
    id("library-android-compose")
}

android {
    namespace = "com.example.android.ui.components"
}

dependencies {
    implementation(project(":ui:themes"))

    implementation(libs.core.ktx)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)
}
