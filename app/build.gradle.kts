plugins {
  alias(libs.plugins.kotlinxSerialization)
  alias(libs.plugins.google.devtools.ksp)
  id("practicamarvel.android.application")
  id("practicamarvel.android.application.compose")
  id("practicamarvel.di.library.compose")
}

android {
  namespace = "com.senkou.practicamarvel"

  defaultConfig {
    applicationId = "com.senkou.practicamarvel"
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

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(project(":domain:character"))
  implementation(project(":framework:characters"))
  implementation(project(":framework:core"))
  implementation(project(":feature:characters"))
  implementation(project(":feature:splashscreen"))
  implementation(project(":feature:common"))

  implementation(libs.androidx.activity.compose)
  implementation(libs.room.runtime)
  implementation(libs.room.ktx)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.test.manifest)
}