import com.senkou.practicamarvel.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.google.devtools.ksp")
      }

      dependencies {
        add("implementation", libs.findLibrary("room.ktx").get())
        add("ksp", libs.findLibrary("room.compiler").get())
      }
    }
  }
}