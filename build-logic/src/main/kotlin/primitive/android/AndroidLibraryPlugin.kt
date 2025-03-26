package primitive.android
import com.android.build.gradle.LibraryExtension
import com.cookpad.puree.kmp.androidLibrary
import com.cookpad.puree.kmp.libs
import com.cookpad.puree.kmp.setupAndroid
import com.cookpad.puree.kmp.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            androidLibrary {
                setupAndroid()
            }

            extensions.configure<LibraryExtension> {
                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true
            }
        }
    }
}
