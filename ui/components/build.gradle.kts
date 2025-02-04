plugins {
    id("library-android-compose")
}

android {
    namespace = "com.example.android.ui.components"
}

dependencies {
    implementation(project(":ui:themes"))

    implementation(libs.core.ktx)
}
