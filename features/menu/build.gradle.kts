plugins {
    spotiwrap_android_library
    spotiwrap_compose_library
}

android {
    namespace = Config.Feature.menuNamespace

    defaultConfig {
        buildConfigField("String", "CLIENT_ID", project.property("CLIENT_ID").toString())
        buildConfigField("String", "CAMPAIGN_ID", project.property("CAMPAIGN_ID").toString())
    }
}

dependencies {
    implementation(project(":core:data"))
}