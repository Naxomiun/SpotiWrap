plugins {
    spotiwrap_android_library
    kotlin("kapt")
}

android {
    namespace = Config.Core.Database
}

dependencies {
    implementation(project(":core:auth"))
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.gson)
}