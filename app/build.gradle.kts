plugins {
    alias(libs.plugins.android.application)
}
configurations.all {
    exclude(group = "com.google.guava", module = "listenablefuture")
}
android {
    namespace = "com.example.comptinou"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.comptinou"
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
}

dependencies {
    implementation("com.google.guava:guava:23.0") {
        exclude(group = "com.google.guava", module = "listenablefuture")
    }
    implementation (libs.json)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
