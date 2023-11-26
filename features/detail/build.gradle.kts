plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
}

android {
    namespace = Config.Feature.Detail
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.features.tracks)
    implementation(projects.features.artists)
}