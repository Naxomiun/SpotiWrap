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

fun DependencyHandlerScope.implementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("implementation", bundle)
}

fun DependencyHandlerScope.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.testImplementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("testImplementation", bundle)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandlerScope.androidTestImplementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("androidTestImplementation", bundle)
}

fun DependencyHandlerScope.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandlerScope.coreLibraryDesugaring(dependency: String) {
    add("coreLibraryDesugaring", dependency)
}
