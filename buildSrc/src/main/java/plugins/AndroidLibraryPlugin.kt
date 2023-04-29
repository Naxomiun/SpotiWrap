package plugins

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.InternalLibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import extensions.*

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

            buildTypes {
                release {
                    isMinifyEnabled = true
                    consumerProguardFiles("proguard-rules.pro")
                }

                debug {
                    enableUnitTestCoverage = true
                }
            }
        }

        configureKtlint()

        dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.android)
            if(project.name != "common") {
                implementation(project(":core:common"))
            }
        }

    }
}