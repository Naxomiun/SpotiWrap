package plugins

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import extensions.androidTestImplementation
import extensions.androidTestImplementationBom
import extensions.debugImplementation
import extensions.implementation
import extensions.implementationBom
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class RoomPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        configureRoom()
    }
}

fun Project.configureRoom() {
    dependencies {
        implementation(libs.room.runtime)
        implementation(libs.room.ktx)
        implementation(libs.room.rxjava3)
        implementation(libs.room.guava)
        implementation(libs.room.paging)
    }
}