plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
    spotiwrap_spotify_auth
}

android {
    namespace = Config.Feature.Menu

    defaultConfig {
        buildConfigField("String", "CLIENT_ID", project.property("CLIENT_ID").toString())
        buildConfigField("String", "CAMPAIGN_ID", project.property("CAMPAIGN_ID").toString())
        buildConfigField("String", "CLIENT_BASE_64", project.property("CLIENT_BASE_64").toString())
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":features:profile"))
    implementation(project(":features:tracks"))
    implementation(project(":features:artists"))
    implementation(project(":features:recently"))
}