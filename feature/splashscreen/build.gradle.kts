plugins {
  id("practicamarvel.android.feature")
}

android {
  namespace = "com.senkou.practicamarvel.ui.splash"
}

dependencies {
  implementation(project(":feature:characters"))
}