import extensions.implementation

plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
}

android {
    namespace = Config.Feature.Tracks
}

dependencies {
    implementation(project(":core:data"))
}