plugins {
  id("practicamarvel.android.feature")
  id("practicamarvel.di.library.compose")
}

android {
  namespace = "com.senkou.practicamarvel"
}

dependencies {
  implementation(project(":domain:character"))
}