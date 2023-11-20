plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
    alias(libs.plugins.spotiwrap.android.auth)
}

android {
    namespace = Config.Feature.Login
}

dependencies {
    implementation(projects.core.auth)
    implementation(projects.core.data)
}