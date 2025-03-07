plugins {
    id("matsumo.primitive.kmp.common")
    id("matsumo.primitive.android.library")
    id("matsumo.primitive.android.common")
    id("matsumo.primitive.kmp.android")
    id("matsumo.primitive.kmp.ios")
    id("matsumo.primitive.room")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "com.cookpad.puree"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.infra)
        }
    }
}
