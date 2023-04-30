import extensions.implementation

plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
}

android {
    namespace = Config.Feature.Artists
}

dependencies {
    implementation(project(":core:network"))
}