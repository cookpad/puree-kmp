plugins {
    // Android
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Kotlin Multiplatform (KMP)
    alias(libs.plugins.kmp) apply false
    alias(libs.plugins.room) apply false

    // Kotlin
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false

    // Publishing
    id("cookpad.primitive.publish.nexus")
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.maven.publish) apply false

    // Others
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ksp) apply false
}
