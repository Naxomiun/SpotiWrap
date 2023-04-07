plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.dataNamespace
}

dependencies {
    implementation(libs.androidx.crypto)
}