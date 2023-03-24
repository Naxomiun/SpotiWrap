plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
    plugins {
        register("spotiwrap_android_application") {
            id = "spotiwrap_android_application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }

        register("spotiwrap_android_library") {
            id = "spotiwrap_android_library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.plugin.android)
    implementation(libs.plugin.gradle)
}