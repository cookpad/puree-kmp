package com.cookpad.puree.kmp

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.androidExt(configure: BaseExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("android", configure)
}

internal fun Project.commonExt(configure: CommonExtension<*, *, *, *, *, *>.() -> Unit) {
    val plugin = if (isApplicationProject()) BaseAppModuleExtension::class.java else LibraryExtension::class.java
    (this as ExtensionAware).extensions.configure(plugin, configure)
}

internal fun Project.isApplicationProject(): Boolean {
    return project.extensions.findByType(BaseAppModuleExtension::class.java) != null
}

internal fun Project.isLibraryProject(): Boolean {
    return project.extensions.findByType(LibraryExtension::class.java) != null
}

internal fun Project.isMultiplatformProject(): Boolean {
    return project.extensions.findByType(KotlinMultiplatformExtension::class.java) != null
}

fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.setupAndroid() {
    androidExt {
        defaultConfig {
            targetSdk = libs.version("targetSdk").toInt()
            minSdk = libs.version("minSdk").toInt()

            javaCompileOptions {
                annotationProcessorOptions {
                    arguments += mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                }
            }

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        splits {
            abi {
                isEnable = true
                isUniversalApk = true

                reset()
                include("x86", "x86_64", "armeabi-v7a", "arm64-v8a")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}
