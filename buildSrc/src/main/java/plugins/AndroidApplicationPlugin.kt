package plugins

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import dependencies.provideAndroidCore
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
        }

    }

}


class test : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {

    }

}
