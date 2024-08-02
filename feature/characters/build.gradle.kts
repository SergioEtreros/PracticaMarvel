plugins {
  id("practicamarvel.android.feature")
  id("practicamarvel.di.library.compose")
}

android {
  namespace = "com.senkou.practicamarvel.ui.characters"
}

dependencies {
  implementation(project(":domain:character"))
}