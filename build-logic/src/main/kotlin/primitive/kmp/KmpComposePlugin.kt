package primitive.kmp

import com.cookpad.puree.androidExt
import com.cookpad.puree.androidTestImplementation
import com.cookpad.puree.debugImplementation
import com.cookpad.puree.implementation
import com.cookpad.puree.library
import com.cookpad.puree.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KmpComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            androidExt {
                buildFeatures.compose = true
            }

            // https://github.com/JetBrains/compose-multiplatform/issues/4711
            configurations.all {
                resolutionStrategy {
                    force("androidx.compose.material:material-ripple:1.7.0-alpha05")
                }
            }

            dependencies {
                val bom = libs.library("androidx-compose-bom")

                implementation(project.dependencies.platform(bom))
                implementation(libs.library("androidx-compose-ui-tooling-preview"))
                debugImplementation(libs.library("androidx-compose-ui-tooling"))
                androidTestImplementation(project.dependencies.platform(bom))
            }
        }
    }
}
