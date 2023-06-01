plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
}

android {
    namespace = Config.Feature.Recommender
}

dependencies {
    implementation(project(":core:data"))
}