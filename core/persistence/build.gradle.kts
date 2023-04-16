import extensions.implementation
import extensions.libs

plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Persistence
}

dependencies {
    implementation(libs.androidx.crypto)
    implementation(libs.gson)
}