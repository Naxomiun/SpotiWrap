plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Auth
}

dependencies {
    implementation(project(":core:persistence"))
}