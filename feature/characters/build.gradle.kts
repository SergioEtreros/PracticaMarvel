plugins {
  id("practicamarvel.android.feature")
}

android {
  namespace = "com.senkou.practicamarvel.ui.characters"
}

dependencies {
  implementation(project(":domain:character"))
}