plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.hbg.reethcast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hbg.reethcast"
        minSdk = 24
        targetSdk = 33
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")
    val nav_version = "2.5.3"
    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.6")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.media3:media3-common:1.2.1")
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation ("io.coil-kt:coil-compose:1.4.0")

    //Iconos Extendidios
    implementation("androidx.compose.material:material-icons-extended:1.3.1")

    //Timber
    implementation ("com.jakewharton.timber:timber:4.7.1")

    //Firebase Firestore
    implementation("com.google.firebase:firebase-firestore:24.10.1")
    implementation("com.google.firebase:firebase-storage-ktx:19.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1")
    // implementation ("com.google.firebase:firebase-firestore-ktx:24.1.0")


    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.11.0")

    // Activity KTX for viewModels()
    implementation ("androidx.activity:activity-ktx:1.1.0")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.28-alpha")
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02")

    // Media3
    implementation ("androidx.media3:media3-session:1.2.1")

    //Coil
    implementation ("io.coil-kt:coil-compose:1.3.2")



}