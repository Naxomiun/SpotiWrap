package plugins

import Config
import com.android.build.api.dsl.ApplicationExtension
import extensions.configureKotlinAndroid
import extensions.configureKtlint
import extensions.implementation
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply {
            apply("com.android.application")
            apply("kotlin-android")
        }

        extensions.configure<ApplicationExtension> {
            namespace = Config.baseApplicationId
            compileSdk = libs.versions.compileSdk.get().toInt()

            defaultConfig {
                targetSdk = libs.versions.targetSdk.get().toInt()
                minSdk = libs.versions.minSdk.get().toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildFeatures {
                buildConfig = true
            }

            buildTypes {

                release {
                    isShrinkResources = true
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    signingConfig = signingConfigs.getByName("debug")
                }

                debug {
                    enableUnitTestCoverage = true
                }

            }

            testOptions.unitTests.all {
                it.useJUnitPlatform()
                it.testLogging {
                    events("passed", "failed", "skipped", "standardOut", "standardError")
                }
            }

            configureKotlinAndroid(this)
            configureKtlint()
            configureCompose(this)

            dependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.android)
                implementation(libs.koin.workmanager)
            }
        }
    }
}