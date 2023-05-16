plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Network
}

dependencies {
    implementation(project(":core:auth"))
    implementation(libs.ktor.core)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.serializationJson)
    implementation(libs.loggingInterceptor)
}