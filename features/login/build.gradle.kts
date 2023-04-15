plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
    spotiwrap_spotify_auth
}

android {
    namespace = Config.Feature.Login

    defaultConfig {
        buildConfigField("String", "CLIENT_ID", project.property("CLIENT_ID").toString())
        buildConfigField("String", "CAMPAIGN_ID", project.property("CAMPAIGN_ID").toString())
    }
}

dependencies {
    implementation(project(":core:auth"))
}