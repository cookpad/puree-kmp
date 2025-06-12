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
    implementation(libs.atomic.gradlePlugin)
    implementation(libs.room.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.maven.publish.gradlePlugin)
    implementation(libs.nexus.publish.gradlePlugin)
    implementation(libs.dokka.gradlePlugin)
}

gradlePlugin {
    plugins {
        // KMP
        register("KmpPlugin") {
            id = "cookpad.primitive.kmp.common"
            implementationClass = "primitive.kmp.KmpCommonPlugin"
        }
        register("KmpAndroidPlugin") {
            id = "cookpad.primitive.kmp.android"
            implementationClass = "primitive.kmp.KmpAndroidPlugin"
        }
        register("KmpIosPlugin") {
            id = "cookpad.primitive.kmp.ios"
            implementationClass = "primitive.kmp.KmpIosPlugin"
        }

        // Android
        register("AndroidCommonPlugin") {
            id = "cookpad.primitive.android.common"
            implementationClass = "primitive.android.AndroidCommonPlugin"
        }
        register("KmpAndroidApplication") {
            id = "cookpad.primitive.android.application"
            implementationClass = "primitive.android.AndroidApplicationPlugin"
        }
        register("KmpAndroidLibrary") {
            id = "cookpad.primitive.android.library"
            implementationClass = "primitive.android.AndroidLibraryPlugin"
        }
        register("AndroidComposePlugin") {
            id = "cookpad.primitive.android.compose"
            implementationClass = "primitive.android.AndroidComposePlugin"
        }

        // Libraries
        register("DetektPlugin") {
            id = "cookpad.primitive.detekt"
            implementationClass = "primitive.DetektPlugin"
        }
        register("RoomPlugin") {
            id = "cookpad.primitive.room"
            implementationClass = "primitive.RoomPlugin"
        }

        // Publishing
        register("NexusPublishPlugin") {
            id = "cookpad.primitive.publish.nexus"
            implementationClass = "primitive.publish.NexusPublishPlugin"
        }
        register("MavenPublishPlugin") {
            id = "cookpad.primitive.publish.maven"
            implementationClass = "primitive.publish.MavenPublishPlugin"
        }
    }
}
