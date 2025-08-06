package primitive.android

import com.cookpad.puree.kmp.isMultiplatformProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import primitive.kmp.kotlin

class AndroidCommonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                if (!isMultiplatformProject()) {
                    apply("kotlin-android")
                }

                apply("kotlin-parcelize")
                apply("kotlinx-serialization")
                apply("project-report")
                apply("com.google.devtools.ksp")
            }

            kotlin {
                androidTarget {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                        freeCompilerArgs.addAll(
                            "-Xopt-in=kotlinx.coroutines.FlowPreview",                          // required for flow.debounce
                            "-Xopt-in=kotlinx.coroutines.DelicateCoroutinesApi",                // Global scope
                            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi"    // Compose
                        )
                    }
                }
            }
        }
    }
}
