plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.compose.library)
    alias(libs.plugins.spotiwrap.android.auth)
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
    implementation(projects.core.data)
    implementation(projects.features.profile)
    implementation(projects.features.tracks)
    implementation(projects.features.artists)
    implementation(projects.features.recently)
}