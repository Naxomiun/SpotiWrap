plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
    alias(libs.plugins.spotiwrap.android.auth)
}

android {
    namespace = Config.Feature.Login
}

dependencies {
    implementation(project(":core:auth"))
    implementation(project(":core:data"))
}