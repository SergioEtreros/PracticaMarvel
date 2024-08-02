plugins {
  id("practicamarvel.android.library.compose")
  alias(libs.plugins.kotlinxSerialization)
}

android {
  namespace = "com.senkou.practicamarvel.ui.common"
}

dependencies {
  implementation(libs.androidx.activity.compose)
}