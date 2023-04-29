package plugins

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.InternalLibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extensions.libs
import extensions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import extensions.*
import org.gradle.api.JavaVersion

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        plugins.apply("com.android.library")
        plugins.apply("kotlin-android")

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
                if (project.name != "common") {
                    implementation(project(":core:common"))
                }
            }
        }
    }
}