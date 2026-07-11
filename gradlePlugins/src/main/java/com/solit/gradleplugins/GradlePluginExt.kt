package com.solit.gradleplugins

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

internal val Project.versionCatalog: VersionCatalog
    get() =
        extensions
            .getByType(VersionCatalogsExtension::class.java)
            .named("libs")

internal fun VersionCatalog.getPlugin(alias: String): String =
    findPlugin(alias).get().get().pluginId

internal fun VersionCatalog.getVersion(alias: String): String {
    val constraint = findVersion(alias).get()
    return when {
        constraint.strictVersion.isNotEmpty() -> constraint.strictVersion
        constraint.requiredVersion.isNotEmpty() -> constraint.requiredVersion
        constraint.preferredVersion.isNotEmpty() -> constraint.preferredVersion
        else -> "unknown"
    }
}

internal fun Project.android(config: LibraryExtension.() -> Unit) {
    extensions.getByType(LibraryExtension::class.java).apply(config)
}
