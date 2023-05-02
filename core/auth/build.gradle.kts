plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Auth

    defaultConfig {
        buildConfigField("String", "CLIENT_ID", project.property("CLIENT_ID").toString())
        buildConfigField("String", "CAMPAIGN_ID", project.property("CAMPAIGN_ID").toString())
        buildConfigField("String", "CLIENT_BASE_64", project.property("CLIENT_BASE_64").toString())
    }
}

dependencies {
    implementation(project(":core:persistence"))
}