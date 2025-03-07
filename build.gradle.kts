plugins {
    // Android
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Kotlin Multiplatform (KMP)
    alias(libs.plugins.kmp) apply false
    alias(libs.plugins.kmpCompose) apply false

    // Kotlin
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false

    // Others
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ksp) apply false
}
