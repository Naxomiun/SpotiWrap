package plugins

import com.android.build.gradle.LibraryExtension
import extensions.configureKotlinAndroid
import extensions.configureKtlint
import extensions.implementation
import extensions.libs
import extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        plugins.apply {
            apply("com.android.library")
            apply("kotlin-android")
            apply("kotlinx-serialization")
        }

        extensions.configure<LibraryExtension> {
            compileSdk = libs.versions.compileSdk.get().toInt()

            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildFeatures {
                buildConfig = true
            }

            buildTypes {
                release {
                    isMinifyEnabled = true
                    consumerProguardFiles("proguard-rules.pro")
                }

                debug {
                    enableUnitTestCoverage = true
                }
            }

            configureKotlinAndroid(this)
            configureKtlint()

            dependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.android)
                implementation(libs.koin.workmanager)
                if (project.name != "common") {
                    implementation(project(":core:common"))
                }
                testImplementation(libs.bundles.unitTest)
                testImplementation(project(":test:shared-test"))
            }
        }
    }
}




