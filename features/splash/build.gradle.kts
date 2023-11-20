plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
    alias(libs.plugins.spotiwrap.android.auth)
}

android {
    namespace = Config.Feature.Splash
}

dependencies {
    implementation(project(":core:auth"))
}