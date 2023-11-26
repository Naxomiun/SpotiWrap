plugins {
    alias(libs.plugins.spotiwrap.android.application)
}

android {
    defaultConfig {
        manifestPlaceholders["redirectSchemeName"] = "spotiwrap"
        manifestPlaceholders["redirectHostName"] = "auth"
    }
}

dependencies {
    implementation(projects.features.login)
    implementation(projects.features.home)
    implementation(projects.features.splash)
    implementation(projects.features.tracks)
    implementation(projects.features.artists)
    implementation(projects.features.profile)
    implementation(projects.features.recommender)
    implementation(projects.features.recently)
    implementation(projects.features.collage)
    implementation(projects.features.top)
    implementation(projects.features.detail)
    implementation(projects.core.common)
    implementation(projects.core.auth)
    implementation(projects.core.persistence)
    implementation(projects.core.data)
    implementation(projects.core.navigation)
    implementation(projects.core.design)
    implementation(projects.core.network)
    implementation(projects.core.database)

    implementation(libs.androidx.workmanager)
}

