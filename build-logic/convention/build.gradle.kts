plugins {
  `kotlin-dsl`
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidApplication") {
      id = "practicamarvel.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    register("androidApplicationCompose") {
      id = "practicamarvel.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    register("androidFeature") {
      id = "practicamarvel.android.feature"
      implementationClass = "AndroidFeatureConventionPlugin"
    }
    register("androidLibraryCompose") {
      id = "practicamarvel.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }
    register("androidLibrary") {
      id = "practicamarvel.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidRoom") {
      id = "practicamarvel.android.room"
      implementationClass = "AndroidRoomConventionPlugin"
    }
    register("jvmLibrary") {
      id = "practicamarvel.jvm.library"
      implementationClass = "JvmLibraryConventionPlugin"
    }
    register("jvmRetrofit") {
      id = "practicamarvel.jvm.retrofit"
      implementationClass = "JvmRetrofitConventionPlugin"
    }
  }
}