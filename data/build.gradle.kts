plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerPlugin)
}

android {
    namespace = ProjectProperties.NAME_SPACE_DATA
    compileSdk = ProjectProperties.COMPLIDE_SDK_VERSION

    defaultConfig {
        minSdk = ProjectProperties.MINSDK_VERSION
        targetSdk = ProjectProperties.TAGETSDK_VERSION

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

    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.MOCKITO)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    // coroutine
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)

    // hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    // room
    implementation(AndroidX.ROOM_RUNTIME)
    kapt(AndroidX.ROOM_COMPILER)
    implementation(AndroidX.ROOM_KTX)

    // retrofit
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // firebase
    implementation(Google.FIREBASE_ANALYTICS)
    implementation(platform(Google.FIREBASE_BOM))
    implementation(Google.FIREBASE_DATABASE)
    implementation(Google.FIREBASE_MESSAGING)
    implementation(Google.FIREBSAE_FIRESTORE)

    implementation(project(ProjectProperties.PATH_DOMAIN))

}