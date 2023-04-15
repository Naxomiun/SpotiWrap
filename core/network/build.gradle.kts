import extensions.implementation
import extensions.libs

plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Network
}

dependencies {
    implementation(project(":core:auth"))
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.loggerInterceptor)
    implementation(libs.retrofit.gson)
}