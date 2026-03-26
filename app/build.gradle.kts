plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dlna"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dlna"
        minSdk = 24
        targetSdk = 34
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Media3 (ExoPlayer)
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")

    // DLNA (Cling fork)
    implementation(files("libs/DM-UPnP-Android-1.4.9-STABLE.jar"))

    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation "com.google.android.material:material:1.10.0" // or latest stable
    implementation "androidx.core:core-splashscreen:1.0.1" // for SplashScreen API
}
