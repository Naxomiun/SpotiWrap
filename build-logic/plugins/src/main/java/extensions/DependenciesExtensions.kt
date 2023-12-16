package extensions

import org.gradle.api.Project
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

fun DependencyHandlerScope.implementation(group: Provider<MinimalExternalModuleDependency>) {
    add("implementation", group)
}

fun DependencyHandlerScope.implementationBom(bom: Provider<MinimalExternalModuleDependency>) {
    add("implementation", bom)
}

fun DependencyHandlerScope.implementation(project: Project) {
    add("implementation", project)
}

fun DependencyHandlerScope.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandlerScope.kapt(dependency: Provider<MinimalExternalModuleDependency>) {
    add("kapt", dependency)
}

fun DependencyHandlerScope.ksp(dependency: String) {
    add("ksp", dependency)
}

fun DependencyHandlerScope.ksp(dependency: Provider<MinimalExternalModuleDependency>) {
    add("ksp", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: Provider<ExternalModuleDependencyBundle>) {
    add("testImplementation", dependency)
}

fun DependencyHandlerScope.testImplementation(project: Project) {
    add("testImplementation", project)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandlerScope.androidTestImplementation(bundle: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", bundle)
}

fun DependencyHandlerScope.androidTestImplementationBom(bom: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", bom)
}

fun DependencyHandlerScope.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandlerScope.debugImplementation(group: Provider<MinimalExternalModuleDependency>) {
    add("debugImplementation", group)
}

fun DependencyHandlerScope.coreLibraryDesugaring(dependency: String) {
    add("coreLibraryDesugaring", dependency)
}

fun DependencyHandlerScope.api(group: Provider<MinimalExternalModuleDependency>) {
    add("api", group)
}

fun DependencyHandlerScope.getBom(dependency: Provider<MinimalExternalModuleDependency>) = platform(dependency)

