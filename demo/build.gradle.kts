plugins {
    id("cookpad.primitive.android.application")
    id("cookpad.primitive.android.common")
    id("cookpad.primitive.android.compose")
    id("cookpad.primitive.detekt")
}

android {
    namespace = "com.cookpad.puree.demo"
}

dependencies {
    implementation(project(":puree"))

    implementation(libs.bundles.ui.common)
    implementation(libs.bundles.ui.android)
    implementation(libs.bundles.infra)
}
