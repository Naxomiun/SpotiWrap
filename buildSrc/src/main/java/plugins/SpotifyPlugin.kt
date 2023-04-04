package plugins

import com.android.build.api.dsl.ApplicationExtension
import extensions.implementation
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class SpotifyPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {

        extensions.configure<ApplicationExtension> {
            defaultConfig {
                addManifestPlaceholders(mapOf("redirectSchemeName" to "spotiwrap", "redirectHostName" to "auth").toMutableMap())
            }
        }

        dependencies {
            implementation(libs.spotify)
        }
    }
}