import com.senkou.practicamarvel.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("practicamarvel.android.library.compose")
      }

      dependencies {
        add("implementation", project(":feature:common"))
        add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
      }
    }
  }
}