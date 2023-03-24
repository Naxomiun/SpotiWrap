package plugins

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import extensions.*

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        plugins.apply("com.android.application")
        plugins.apply("kotlin-android")

        extensions.configure<BaseAppModuleExtension> {
            namespace = Config.baseApplicationId
            compileSdk = libs.versions.compileSdk.get().toInt()

            defaultConfig {
                targetSdk = libs.versions.targetSdk.get().toInt()
                minSdk = libs.versions.minSdk.get().toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                release {
                    isMinifyEnabled = libs.versions.releaseMinifyEnabled.get().toBoolean()
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
                    )
                }
            }
        }

        dependencies {
            implementation(libs.bundles.compose)
            testImplementation(libs.bundles.unitTest)
            androidTestImplementation(libs.bundles.instrumentationTest)
        }
    }
}