import java.util.Properties

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerPlugin)
    id(Plugins.navigationSafeArgs)
    id("com.google.gms.google-services")
}

val prperties = Properties()
prperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = ProjectProperties.APPLIACATION_ID
    compileSdk = ProjectProperties.COMPLIDE_SDK_VERSION

    defaultConfig {
        applicationId = ProjectProperties.APPLIACATION_ID
        minSdk = ProjectProperties.MINSDK_VERSION
        targetSdk = ProjectProperties.TAGETSDK_VERSION
        versionCode = ProjectProperties.VERSION_CODE
        versionName = ProjectProperties.VERSION_NAME
        buildConfigField("String", "KAKAO_KEY", "${prperties["kakao_api_key"]}")
        manifestPlaceholders["KAKAO_KEY"] = prperties["kakao_api_key"].toString()

        testInstrumentationRunner = ProjectProperties.TEST_RUNER
        vectorDrawables {
            useSupportLibrary = ProjectProperties.USE_SUPPORT_LIBRARY
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = ProjectProperties.IS_MINIFY_ENABLED
            proguardFiles(getDefaultProguardFile(ProjectProperties.PROGUARD_NAME), ProjectProperties.PROGUARD_FILE)
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.JAVA_VERSION
        targetCompatibility = ProjectProperties.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.JVM_TARGET
    }

    buildFeatures {
        dataBinding = ProjectProperties.DATABINDING
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.CONSTRAINTLAYOUT)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")


    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)

    // coroutine
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)

    // retrofit
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    // hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    // dataBinding
//    kapt(AndroidX.DATABINDING)

    // navigation
    implementation(AndroidX.NAVIGATION_FRAGMENT)
    implementation(AndroidX.NAVIGATION_UI)

    // Material
    implementation(Google.MATERIAL)

    // glide
    implementation(Libraries.GLIDE)
    kapt(Libraries.GLIDE_COMPILER)
//    implementation("com.google.android.material:material:1.6.0")
    implementation(project(ProjectProperties.PATH_DOMAIN))
    implementation(project(ProjectProperties.PATH_DATA))
    implementation(project(ProjectProperties.PATH_DI))

    // firebase
    implementation(Google.FIREBASE_ANALYTICS)
    implementation(platform(Google.FIREBASE_BOM))
    implementation(Google.FIREBASE_DATABASE)
    implementation(Google.FIREBASE_MESSAGING)
    implementation(Google.FIREBSAE_FIRESTORE)
    // kakao
    implementation ("com.kakao.sdk:v2-user:2.15.0") // 카카오 로그인

    // cricle imageView
    implementation("de.hdodenhof:circleimageview:3.1.0")
}