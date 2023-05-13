plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
}

android {
    namespace = Config.Feature.Artists
    sourceSets {
        getByName("main") {
            resources.srcDirs("src/main/fonts")
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    testImplementation(project(":core:network"))
}