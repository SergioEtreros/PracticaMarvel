plugins {
  id("practicamarvel.jvm.library")
  id("practicamarvel.di.library")
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
}