import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.binet.G3b8qZe9Wk2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.binet.G3b8qZe9Wk2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)
    implementation("androidx.fragment:fragment-ktx:1.8.4")

    
//    implementation(libs.ktor.client.core)
//    implementation(libs.ktor.client.android)
//    implementation(libs.ktor.client.serialization)
//    implementation(libs.ktor.client.logging)
//
//
//    compileOnly(libs.koin.core)
//    implementation(libs.koin.android)
//    implementation(libs.koin.androidx.viewmodel)
//
//
//    implementation(libs.androidx.appcompat)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)

    implementation(libs.hannesdorfmann.adapterdelegates4.kotlin.dsl)
    implementation(libs.coil)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}