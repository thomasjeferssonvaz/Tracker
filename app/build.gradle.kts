plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.android.library") version "8.1.0" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}



android {
    namespace = "com.thomas.tracker"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }


    defaultConfig {
        applicationId = "com.thomas.tracker"
        minSdk = 34
        targetSdk = 34
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

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.androidx.lifecycle.livedata.ktx)
        implementation(libs.androidx.lifecycle.viewmodel.ktx)
        implementation(libs.androidx.navigation.fragment.ktx)
        implementation(libs.androidx.navigation.ui.ktx)
        implementation(libs.play.services.location)
        implementation(libs.play.services.maps)
        implementation("com.google.android.gms:play-services-maps:17.0.0")
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0") // For JSON parsing
        implementation("com.google.code.gson:gson:2.10.1") // Gson library
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
    }
    secrets {

    }
}
