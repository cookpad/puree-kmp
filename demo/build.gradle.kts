plugins {
    id("matsumo.primitive.android.application")
    id("matsumo.primitive.android.common")
    id("matsumo.primitive.android.compose")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "com.cookpad.puree.demo"
}

dependencies {
    implementation(project(":puree"))
    implementation(libs.bundles.ui.common)
    implementation(libs.bundles.ui.android)
    implementation(libs.bundles.infra)

    implementation(libs.androidx.core.splashscreen)
}
