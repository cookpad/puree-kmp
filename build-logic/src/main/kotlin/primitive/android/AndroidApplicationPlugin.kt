package primitive.android
import com.cookpad.puree.androidApplication
import com.cookpad.puree.libs
import com.cookpad.puree.setupAndroid
import com.cookpad.puree.version
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            androidApplication {
                setupAndroid()

                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true

                defaultConfig {
                    applicationId = "com.cookpad.puree"

                    versionName = libs.version("versionName")
                    versionCode = libs.version("versionCode").toInt()
                }

                packaging {
                    resources.excludes.addAll(
                        listOf(
                            "LICENSE",
                            "LICENSE.txt",
                            "NOTICE",
                        )
                    )
                }
            }
        }
    }
}
