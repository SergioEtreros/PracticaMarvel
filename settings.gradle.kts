@file:Suppress("UnstableApiUsage")
pluginManagement {
  includeBuild("build-logic")
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Practica Marvel"
include(":app")

include(":domain:character")

include(":feature:common")
include(":feature:characters")
include(":feature:splashscreen")

include(":framework:core")
include(":framework:characters")
