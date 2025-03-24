package primitive.publish

import com.cookpad.puree.libs
import com.cookpad.puree.version
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.plugins.signing.SigningExtension
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier

class MavenPublishPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("maven-publish")
                apply("signing")
                apply("com.vanniktech.maven.publish")
                apply("org.jetbrains.dokka")
                apply("org.jetbrains.dokka-javadoc")
            }

            allprojects {
                group = "com.cookpad.puree"
                version = libs.version("versionName")
            }

            afterEvaluate {
                tasks.filter { it.name.contains("SourcesJar", true) }.forEach {
                    it.dependsOn("kspCommonMainKotlinMetadata")
                }
            }

            configureDokka()
            configureMavenPublish()
            configureSigning()
        }
    }

    private fun Project.configureDokka() {
        extensions.configure<DokkaExtension> {
            dokkaSourceSets.getByName("commonMain") {
                enableAndroidDocumentationLink = true
                enableKotlinStdLibDocumentationLink = true

                documentedVisibilities.set(
                    setOf(
                        VisibilityModifier.Public,
                        // VisibilityModifier.Internal,
                    )
                )
            }

            pluginsConfiguration.getByName("html") {
                moduleVersion = libs.version("versionName")
            }

            dokkaPublications.getByName("html") {
                outputDirectory = file("$rootDir/dokka")
            }

            dependencies {
                add("dokka", project(":puree"))
            }
        }
    }

    private fun Project.configureMavenPublish() {
        extensions.configure<MavenPublishBaseExtension> {
            configure(KotlinMultiplatform(javadocJar = JavadocJar.Dokka("dokkaGeneratePublicationHtml")))
            publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, true)

            val hasUserNameFromProject = project.hasProperty("mavenCentralUsername")
            val hasUserNameFromEnv = System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null

            if (hasUserNameFromProject || hasUserNameFromEnv) {
                signAllPublications()
            }

            coordinates(
                groupId = group.toString(),
                version = version.toString(),
                artifactId = project.name,
            )

            pom {
                name = "puree-kmp"
                description = "puree-kmp is a log collector for iOS/Android kmp library."
                url = "https://github.com/cookpad/puree-kmp"

                scm {
                    connection = "scm:git@github.com:cookpad/puree.git"
                    developerConnection = "scm:git@github.com:cookpad/puree.git"
                    url = "scm:git@github.com:cookpad/puree.git"
                }

                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/mit-license"
                    }
                }

                developers {
                    developer {
                        id = "Cookpad"
                        name = "Cookpad Inc."
                    }
                }
            }
        }
    }

    private fun Project.configureSigning() {
        extensions.configure<SigningExtension> {
            val hasUserNameFromProject = project.hasProperty("mavenCentralUsername")
            val hasUserNameFromEnv = System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null

            if (hasUserNameFromProject || hasUserNameFromEnv) {
                useGpgCmd()
            }
        }
    }
}
