plugins {
    alias(libs.plugins.spotiwrap.android.library)
}

android {
    namespace = Config.Tests.SharedTests
}

dependencies {
    implementation(project(":core:network"))
}