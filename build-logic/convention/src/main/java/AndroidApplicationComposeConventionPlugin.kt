import com.android.build.api.dsl.ApplicationExtension
import com.senkou.practicamarvel.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
//        apply("practicamarvel.android.library.compose")
//        apply("org.jetbrains.kotlin.android")
//        apply("org.jetbrains.kotlin.plugin.serialization")
        val extensions = extensions.getByType<ApplicationExtension>()
        configureAndroidCompose(extensions)
      }


//      dependencies {
//        add("implementation", project(":feature:common"))
//        add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
//      }
    }
  }
}