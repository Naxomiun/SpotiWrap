plugins {
    spotiwrap_android_application
}

android {
    defaultConfig {
        manifestPlaceholders["redirectSchemeName"] = "spotiwrap"
        manifestPlaceholders["redirectHostName"] = "auth"
    }
}

dependencies {
    implementation(project(":features:login"))
    implementation(project(":features:menu"))
    implementation(project(":features:splash"))
    implementation(project(":features:tracks"))
    implementation(project(":features:artists"))
    implementation(project(":features:profile"))
    implementation(project(":core:common"))
    implementation(project(":core:auth"))
    implementation(project(":core:persistence"))
    implementation(project(":core:network"))
    implementation(project(":core:navigation"))
    implementation(project(":core:design"))
}

