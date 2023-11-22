package plugins

import com.android.build.gradle.LibraryExtension
import extensions.implementation
import extensions.kapt
import extensions.ksp
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply {
            apply("com.google.devtools.ksp")
        }

        extensions.configure<LibraryExtension> {

            dependencies {
                implementation(libs.room.runtime)
                implementation(libs.room.ktx)
                ksp(libs.room.compiler)
                implementation(libs.gson)
            }

        }
    }

}


