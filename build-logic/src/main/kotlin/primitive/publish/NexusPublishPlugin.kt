package primitive.publish

import io.github.gradlenexus.publishplugin.NexusPublishExtension
import io.github.gradlenexus.publishplugin.NexusPublishPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class NexusPublishPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<NexusPublishPlugin>()

            extensions.getByType<NexusPublishExtension>().apply {
                // Configure maven central repository
                // https://github.com/gradle-nexus/publish-plugin#publishing-to-maven-central-via-sonatype-ossrh
                repositories {
                    sonatype()
                }
            }
        }
    }
}
