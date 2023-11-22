import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.wachon.spotiwrap.buildlogic"

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        register("spotiwrapAndroidApplication") {
            id = "spotiwrap.android.application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }

        register("spotiwrapSpotifyAuth") {
            id = "spotiwrap.spotify.auth"
            implementationClass = "plugins.SpotifyPlugin"
        }

        register("spotiwrapAndroidLibrary") {
            id = "spotiwrap.android.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }

        register("spotiwrapComposeLibrary") {
            id = "spotiwrap.compose.library"
            implementationClass = "plugins.ComposePlugin"
        }

        register("spotiwrapRoomLibrary") {
            id = "spotiwrap.room.library"
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
    implementation(libs.plugin.kotlin.ksp)
}