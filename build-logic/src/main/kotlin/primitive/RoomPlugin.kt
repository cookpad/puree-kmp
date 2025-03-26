package primitive

import androidx.room.gradle.RoomExtension
import com.cookpad.puree.kmp.bundle
import com.cookpad.puree.kmp.library
import com.cookpad.puree.kmp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import primitive.kmp.kotlin

class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("androidx.room")

            kotlin {
                sourceSets.commonMain.dependencies {
                    implementation(libs.bundle("database"))
                }
            }

            dependencies {
                listOf(
                    "kspAndroid",
                    "kspIosSimulatorArm64",
                    "kspIosX64",
                    "kspIosArm64"
                ).forEach {
                    add(it, libs.library("kmp-room-compiler"))
                }
            }

            extensions.getByType<RoomExtension>().apply {
                schemaDirectory("$projectDir/schemas")
            }
        }
    }
}
