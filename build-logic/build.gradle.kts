plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.room.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        // KMP
        register("KmpPlugin") {
            id = "matsumo.primitive.kmp.common"
            implementationClass = "primitive.kmp.KmpCommonPlugin"
        }
        register("KmpAndroidPlugin") {
            id = "matsumo.primitive.kmp.android"
            implementationClass = "primitive.kmp.KmpAndroidPlugin"
        }
        register("KmpIosPlugin") {
            id = "matsumo.primitive.kmp.ios"
            implementationClass = "primitive.kmp.KmpIosPlugin"
        }
        register("KmpAndroidCompose") {
            id = "matsumo.primitive.kmp.compose"
            implementationClass = "primitive.kmp.KmpComposePlugin"
        }

        // Android
        register("AndroidCommonPlugin") {
            id = "matsumo.primitive.android.common"
            implementationClass = "primitive.android.AndroidCommonPlugin"
        }
        register("KmpAndroidApplication") {
            id = "matsumo.primitive.android.application"
            implementationClass = "primitive.android.AndroidApplicationPlugin"
        }
        register("KmpAndroidLibrary") {
            id = "matsumo.primitive.android.library"
            implementationClass = "primitive.android.AndroidLibraryPlugin"
        }
        register("AndroidComposePlugin") {
            id = "matsumo.primitive.android.compose"
            implementationClass = "primitive.android.AndroidComposePlugin"
        }

        // Libraries
        register("DetektPlugin") {
            id = "matsumo.primitive.detekt"
            implementationClass = "primitive.DetektPlugin"
        }
        register("RoomPlugin") {
            id = "matsumo.primitive.room"
            implementationClass = "primitive.RoomPlugin"
        }
    }
}
