import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

gradlePlugin {
    plugins {
        register("spotiwrap_android_application") {
            id = "spotiwrap_android_application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }

        register("spotiwrap_spotify_auth") {
            id = "spotiwrap_spotify_auth"
            implementationClass = "plugins.SpotifyPlugin"
        }

        register("spotiwrap_android_library") {
            id = "spotiwrap_android_library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }

        register("spotiwrap_compose_library") {
            id = "spotiwrap_compose_library"
            implementationClass = "plugins.ComposePlugin"
        }

        register("spotiwrap_room_library") {
            id = "spotiwrap_room_library"
            implementationClass = "plugins.AndroidRoomPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
    gradlePluginPortal()

}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.plugin.android)
    implementation(libs.plugin.gradle)
    implementation(libs.plugin.ktlint)
    implementation(libs.plugin.kotlin.serialization)
}