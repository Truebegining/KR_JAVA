plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs") // Safe Args для Kotlin
    id("kotlin-parcelize") // Плагин для Parcelable
}

android {
    namespace = "com.example.kr_kotlin"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kr_kotlin"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.13.0")) // Используйте BoM для управления версиями
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore-ktx") // Версия управляется BoM
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.0") // Явное указание версии
    implementation("androidx.navigation:navigation-ui-ktx:2.9.0") // Совместимость с Safe Args 2.9.0

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Тестирование
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}