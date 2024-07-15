plugins {
  id("practicamarvel.android.library.compose")
}

android {
  namespace = "com.senkou.practicamarvel.ui.common"
}

dependencies {
  implementation(libs.androidx.activity.compose)
}