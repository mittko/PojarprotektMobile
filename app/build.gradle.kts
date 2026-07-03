plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("D:\\PojarprotektMobile\\keystorefile.jks")
            storePassword = "pojarprotekt"
            keyPassword = "pojarprotekt"
            keyAlias = "key0"
        }
        create("release") {
            storeFile = file("D:\\PojarprotektMobile\\keystorefile.jks")
            storePassword = "pojarprotekt"
            keyAlias = "key0"
            keyPassword = "pojarprotekt"
        }
    }
    namespace = "com.example.fireextinguishinginstallationsmobile"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.fireextinguishinginstallationsmobile"
        minSdk = 25
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
            signingConfig = signingConfigs.getByName("release")
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
        compose = true
    }

    configurations.all {
        exclude(group = "androidx.wear.compose", module = "compose-material-core")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.ui)
    implementation(libs.androidx.interpolator)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.constraintlayout.compose)
    implementation(platform(libs.androidx.compose.bom.v20260101))
    implementation(libs.gson)
    implementation(libs.converter.scalars)
    // gson converter
    implementation(libs.converter.gson)
    //  implementation(libs.logging.interceptor)
    implementation(libs.signature.pad)
    // implementation("com.seanproctor:signaturepad:1.0.1")
    implementation(libs.androidx.compose.material.icons.extended)
    // В build.gradle.kts (Module: app)
    // Използвай изрично Android-специфичния пакет, за да спре да се бърка Gradle
    implementation("androidx.lifecycle:lifecycle-runtime-android:2.8.7")



    implementation("androidx.core:core-splashscreen:1.2.0")
    implementation("androidx.camera:camera-core:1.6.1")
    implementation("androidx.camera:camera-view:1.4.1")
    implementation("androidx.camera:camera-lifecycle:1.6.1")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    // Check for the latest version if available
    implementation("org.danilopianini:gson-extras:3.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("androidx.compose.material3:material3:1.4.0")


}