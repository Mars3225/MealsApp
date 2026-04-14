plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}

configurations {
    create("cleanedAnnotations")
    implementation {
        exclude(group = "org.jetbrains", module = "annotations")
    }
}

android {
    namespace = "com.example.mealsapp"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }



    defaultConfig {
        applicationId = "com.example.mealsapp"
        minSdk = 24
        targetSdk = 36
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("io.coil-kt.coil3:coil-compose:3.4.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.4.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")
    val room_version = "2.7.0-alpha11"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
}