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
    implementation(project(":core:data"))
    implementation(project(":core:navigation"))
}