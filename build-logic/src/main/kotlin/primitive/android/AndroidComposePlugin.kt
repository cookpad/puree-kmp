package primitive.android

import com.cookpad.puree.androidTestImplementation
import com.cookpad.puree.commonExt
import com.cookpad.puree.debugImplementation
import com.cookpad.puree.implementation
import com.cookpad.puree.library
import com.cookpad.puree.libs
import com.cookpad.puree.plugin
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
