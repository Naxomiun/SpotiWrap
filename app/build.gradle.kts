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

    implementation(project(":features:login"))
    implementation(project(":features:home"))
    implementation(project(":features:splash"))
    implementation(project(":features:tracks"))
    implementation(project(":features:artists"))
    implementation(project(":features:profile"))
    implementation(project(":features:recommender"))
    implementation(project(":features:recently"))
    implementation(project(":core:common"))
    implementation(project(":core:auth"))
    implementation(project(":core:persistence"))
    implementation(project(":core:data"))
    implementation(project(":core:navigation"))
    implementation(project(":core:design"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    implementation(libs.androidx.workmanager)
}

