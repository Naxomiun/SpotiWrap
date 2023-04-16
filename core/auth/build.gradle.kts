plugins {
    spotiwrap_android_library
}

android {
    namespace = Config.Core.Auth

    defaultConfig {
        buildConfigField("String", "CLIENT_ID", project.property("CLIENT_ID").toString())
        buildConfigField("String", "CAMPAIGN_ID", project.property("CAMPAIGN_ID").toString())
    }
}

dependencies {
    implementation(project(":core:persistence"))
}