package primitive.android

import com.cookpad.puree.isApplicationProject
import com.cookpad.puree.isLibraryProject
import com.cookpad.puree.isMultiplatformProject
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
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

            tasks.withType(KotlinCompile::class.java) {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_17.toString()
                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-Xopt-in=kotlinx.coroutines.FlowPreview",                          // required for flow.debounce
                        "-Xopt-in=kotlinx.coroutines.DelicateCoroutinesApi",                // Global scope
                        "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi"    // Compose
                    )
                }
            }
        }
    }
}
