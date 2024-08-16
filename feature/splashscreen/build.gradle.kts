plugins {
  id("practicamarvel.android.feature")
  id("practicamarvel.di.library.compose")
}

android {
  namespace = "com.senkou.practicamarvel.ui.splash"
}

dependencies {
   implementation(project(":domain:character"))
  implementation(project(":feature:characters"))
   testImplementation(project(":feature:characters"))
}