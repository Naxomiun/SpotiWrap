package extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.implementation(project: ProjectDependency) {
    add("implementation", project)
}

fun DependencyHandlerScope.implementationBom(bom: Provider<MinimalExternalModuleDependency>) {
    add("implementation", bom)
}

fun DependencyHandlerScope.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.testImplementation(bundle: Provider<Any>) {
    add("testImplementation", bundle)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandlerScope.androidTestImplementationBom(bom: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", bom)
}

fun DependencyHandlerScope.androidTestImplementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("androidTestImplementation", bundle)
}

fun DependencyHandlerScope.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandlerScope.debugImplementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("debugImplementation", bundle)
}

fun DependencyHandlerScope.coreLibraryDesugaring(dependency: String) {
    add("coreLibraryDesugaring", dependency)
}

fun DependencyHandlerScope.getBom(dependency: Provider<MinimalExternalModuleDependency>) = platform(dependency)

