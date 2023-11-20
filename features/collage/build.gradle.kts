plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
}

android {
    namespace = Config.Feature.Collage
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:auth"))

}