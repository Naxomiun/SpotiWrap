plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
}

android {
    namespace = Config.Feature.Profile
}

dependencies {
    implementation(project(":core:data"))
}