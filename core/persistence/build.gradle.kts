plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Persistence
}

dependencies {
    implementation(libs.gson)
}