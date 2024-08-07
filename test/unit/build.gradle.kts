plugins {
   id("practicamarvel.jvm.library")
}

dependencies {
   implementation(project(":domain:character"))
   implementation(libs.junit)
   implementation(libs.kotlinx.coroutines.test)
}