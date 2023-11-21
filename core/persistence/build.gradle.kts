plugins {
    alias(libs.plugins.spotiwrap.android.library)
}

android {
    namespace = Config.Core.Persistence
}

dependencies {
    implementation(libs.gson)
}