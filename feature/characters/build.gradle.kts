plugins {
  id("practicamarvel.android.feature")
}

android {
  namespace = "com.senkou.practicamarvel"
}

dependencies {
  implementation(project(":domain:character"))
}