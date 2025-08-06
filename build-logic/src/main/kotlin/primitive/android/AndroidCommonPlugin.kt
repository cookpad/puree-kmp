package primitive.android

import com.cookpad.puree.kmp.isMultiplatformProject
import org.gradle.api.Plugin
import org.gradle.api.Project

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
        }
    }
}
