import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerPlugin)
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())
android {
    namespace = ProjectProperties.NAME_SPACE_DI
    compileSdk = ProjectProperties.COMPLIDE_SDK_VERSION

    defaultConfig {
        minSdk = ProjectProperties.MINSDK_VERSION
        targetSdk = ProjectProperties.TAGETSDK_VERSION

        buildConfigField("String", "SERVER", "${properties["SERVER_URL"]}")
        testInstrumentationRunner = ProjectProperties.TEST_RUNER
    }

    compileOptions {
        sourceCompatibility = ProjectProperties.JAVA_VERSION
        targetCompatibility = ProjectProperties.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.JVM_TARGET
    }
    packagingOptions {
        resources {
            excludes += ProjectProperties.EXCLUDES
        }
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE_KTX)
    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    // coroutine
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)

    // hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    // retrofit
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    // room
    implementation(AndroidX.ROOM_RUNTIME)
    kapt(AndroidX.ROOM_COMPILER)
    implementation(AndroidX.ROOM_KTX)

    // firebase
    implementation(Google.FIREBASE_ANALYTICS)
    implementation(platform(Google.FIREBASE_BOM))
    implementation(Google.FIREBASE_DATABASE)
    implementation(Google.FIREBASE_MESSAGING)
    implementation(Google.FIREBSAE_FIRESTORE)

    implementation(project(ProjectProperties.PATH_DATA))
    implementation(project(ProjectProperties.PATH_DOMAIN))
}
