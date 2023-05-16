plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
    spotiwrap_spotify_auth
}

android {
    namespace = Config.Feature.Login
}

dependencies {
    implementation(project(":core:auth"))
    implementation(project(":core:data"))
}