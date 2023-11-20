plugins {
    alias(libs.plugins.spotiwrap.android.library)
}

android {
    namespace = Config.Core.Data
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(libs.androidx.workmanager)
}