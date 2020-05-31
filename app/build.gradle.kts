import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("androidx.navigation.safeargs.kotlin")
    id("de.mannodermaus.android-junit5")
    id("org.jetbrains.dokka")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.hardiktrivedi.theinternationaldhaba"
        minSdkVersion(26)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.hardiktrivedi.theinternationaldhaba.RecipeTestRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        val options = this as KotlinJvmOptions
        options.jvmTarget = "1.8"
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android:flexbox:1.1.0")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13")

    val androidTestingVersion = "1.2.0"
    debugImplementation("androidx.test:runner:$androidTestingVersion")
    debugImplementation("androidx.test:rules:$androidTestingVersion")
    debugImplementation("androidx.test:core-ktx:$androidTestingVersion")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.1")

    val espressoVersion = "3.2.0"
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:$espressoVersion")
    testImplementation("android.arch.core:core-testing:1.1.1")

    val fragmentTestVersion = "1.1.0"
    debugImplementation("androidx.fragment:fragment-testing:$fragmentTestVersion")

    implementation("com.github.bumptech.glide:glide:4.9.0")

    // Live data and view model
    val lifecycleVersion = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("android.arch.lifecycle:extensions:1.1.1")

    val recyclerViewVersion = "1.1.0"
    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")

    val navVersion = "2.2.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Java
    implementation("androidx.navigation:navigation-fragment:$navVersion")
    implementation("androidx.navigation:navigation-ui:$navVersion")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    val koinVersion = "2.0.1"
    implementation("org.koin:koin-android:$koinVersion") {
        because("A Kotlin specific DI framework")
    }
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion") {
        because("An extension to support easy DI specially for view models")
    }
    // Rx-Android
    val rxAndroid = "3.0.0"
    implementation("io.reactivex.rxjava3:rxandroid:$rxAndroid")
    implementation("io.reactivex.rxjava3:rxjava:$rxAndroid")

    // Network calls
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")

    // Lottie animations
    val lottieVersion = "3.3.1"
    implementation("com.airbnb.android:lottie:$lottieVersion") {
        because("Lottie animations are used to show animated progress loaders")
    }

    val junit5 = "5.5.2"
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit5")

    // (Optional) If you need "Parameterized Tests"
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5")

    val mockkVersion = "1.9.3"
    testImplementation("io.mockk:mockk:$mockkVersion")

    val mockWebserverVersion = "4.3.1"
    debugImplementation("com.squareup.okhttp3:mockwebserver:$mockWebserverVersion")
}

