package primitive
import com.cookpad.puree.configureDetekt
import com.cookpad.puree.library
import com.cookpad.puree.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            configureDetekt()

            dependencies {
                "detektPlugins"(libs.library("detekt-formatting"))
                "detektPlugins"(libs.library("twitter-compose-rule"))
            }
        }
    }
}
