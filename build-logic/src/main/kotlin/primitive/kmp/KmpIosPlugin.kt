package primitive.kmp

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

class KmpIosPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            kotlin {
                applyDefaultHierarchyTemplate()

                val xcf = XCFramework("PureeKMP")

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64(),
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = "PureeKMP"
                        isStatic = false

                        xcf.add(this)
                    }
                }
            }
        }
    }
}
