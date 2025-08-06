package primitive.kmp

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpCommonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlinx.atomicfu")
            }

            kotlin {
                // https://stackoverflow.com/questions/36465824/android-studio-task-testclasses-not-found-in-project
                task("testClasses")

                compilerOptions {
                    compilerOptions {
                        freeCompilerArgs.addAll(
                            "-Xopt-in=kotlinx.coroutines.FlowPreview",                          // required for flow.debounce
                            "-Xopt-in=kotlinx.coroutines.DelicateCoroutinesApi",                // Global scope
                            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi"    // Compose
                        )
                    }
                }
            }

            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink>().configureEach {
                notCompatibleWithConfigurationCache("Configuration chache not supported for a system property read at configuration time")
            }
        }
    }
}

fun Project.kotlin(action: KotlinMultiplatformExtension.() -> Unit) {
    extensions.configure(action)
}
