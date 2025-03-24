plugins {
    id("cookpad.primitive.kmp.common")
    id("cookpad.primitive.android.library")
    id("cookpad.primitive.android.common")
    id("cookpad.primitive.kmp.android")
    id("cookpad.primitive.kmp.ios")
    id("cookpad.primitive.room")
    id("cookpad.primitive.detekt")
}

android {
    namespace = "com.cookpad.puree"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.infra)
            implementation(libs.bundles.ui.common)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.database)
        }

        androidMain.dependencies {
            implementation(libs.androidx.startup)
            implementation(libs.androidx.lifecycle.process)
        }

        androidUnitTest.dependencies {
            implementation(libs.bundles.test.jvm)
        }
    }
}
