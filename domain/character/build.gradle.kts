plugins {
  id("practicamarvel.jvm.library")
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
}