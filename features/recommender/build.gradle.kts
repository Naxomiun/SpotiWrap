plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
}

android {
    namespace = Config.Feature.Recommender
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:auth"))
}