plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Tests.SharedTests
}

dependencies {
    implementation(project(":core:network"))
}