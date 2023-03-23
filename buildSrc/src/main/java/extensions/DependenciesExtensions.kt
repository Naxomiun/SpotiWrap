package extensions

import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.implementation(project: ProjectDependency) {
    add("implementation", project)
}

fun DependencyHandlerScope.implementation(retrofit: Provider<ExternalModuleDependencyBundle>) {
    add("implementation", retrofit)
}

fun DependencyHandlerScope.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandlerScope.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandlerScope.coreLibraryDesugaring(dependency: String) {
    add("coreLibraryDesugaring", dependency)
}
