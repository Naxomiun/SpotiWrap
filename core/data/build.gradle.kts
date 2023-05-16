plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Data
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(libs.androidx.workmanager)
}