plugins {
    id("com.android.application")
}

android {
    namespace = "com.tuk.orderandpay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tuk.orderandpay"
        minSdk = 30
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // Retrofit core
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // JSON 직렬화 (Gson 사용 시)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // (선택) OkHttp 로깅 인터셉터 - 네트워크 디버깅 용도
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
}