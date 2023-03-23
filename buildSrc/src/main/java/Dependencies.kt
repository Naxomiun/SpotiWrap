package dependencies

import extensions.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

fun Project.provideAndroidCore() {
    dependencies {
        implementation(project(":core:android"))




    }
}