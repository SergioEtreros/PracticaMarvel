import java.util.Properties

plugins {
   id("practicamarvel.android.library")
   id("practicamarvel.android.room")
   id("practicamarvel.jvm.retrofit")
  id("practicamarvel.di.library")
}

android {
   namespace = "com.senkou.practicamarvel.framework.core"

   defaultConfig {
      val properties = Properties()
      properties.load(project.rootProject.file("local.properties").readText().byteInputStream())

      val marvelApiKey = properties.getProperty("MARVEL_API_KEY", "")
      buildConfigField("String", "MARVEL_API_KEY", "\"$marvelApiKey\"")

      val marvelPrivApiKey = properties.getProperty("MARVEL_PRIV_API_KEY", "")
      buildConfigField("String", "MARVEL_PRIV_API_KEY", "\"$marvelPrivApiKey\"")

      ksp {
         arg("room.schemaLocation", "$projectDir/schemas")
      }
   }

   buildFeatures {
      buildConfig = true
   }
}

dependencies {
   implementation(project(":framework:characters"))
}