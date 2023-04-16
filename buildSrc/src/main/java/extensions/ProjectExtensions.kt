package extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import extensions.implementation
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.io.File

val Project.libs get() = the<LibrariesForLibs>()

fun Project.configureKtlint() {
    plugins.apply("org.jlleitschuh.gradle.ktlint")
    configure<KtlintExtension> {
        android.set(true)
        ignoreFailures.set(false)
        disabledRules.set(
            setOf(
                "final-newline",
                "no-wildcard-imports",
                "max-line-length"
            )
        )
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
            reporter(ReporterType.SARIF)
        }
    }
    tasks.getByPath("preBuild").dependsOn("ktlintFormat")
}