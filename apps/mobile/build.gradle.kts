plugins {
    id("example-application")
}

android {
    namespace = "com.example.android"

    defaultConfig {
        applicationId = "com.example.android"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":features:feature_one"))
    implementation(project(":ui:themes"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.activity.compose)
}
