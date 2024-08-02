plugins {
  id("practicamarvel.android.library")
  id("practicamarvel.android.room")
  id("practicamarvel.jvm.retrofit")
  id("practicamarvel.di.library")
}

android {
  namespace = "com.senkou.framework.characters"
}

dependencies {
  implementation(project(":domain:character"))
}