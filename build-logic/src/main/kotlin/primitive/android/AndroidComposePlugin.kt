package primitive.android

import com.cookpad.puree.kmp.androidTestImplementation
import com.cookpad.puree.kmp.commonExt
import com.cookpad.puree.kmp.debugImplementation
import com.cookpad.puree.kmp.implementation
import com.cookpad.puree.kmp.library
import com.cookpad.puree.kmp.libs
import com.cookpad.puree.kmp.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugin("kotlin-compose-compiler").pluginId)
            }

            commonExt {
                buildFeatures.compose = true
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
