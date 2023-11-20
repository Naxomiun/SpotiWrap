plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
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
    implementation(projects.core.data)
    testImplementation(projects.core.network)
}