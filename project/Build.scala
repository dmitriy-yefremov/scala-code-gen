import sbt._
import Keys._

import twirl.sbt.TwirlPlugin._

object Build extends Build {

  /**
   * A dummy aggregator project.
   */
  lazy val root = project.in(file(".")).aggregate(generator, sample)

  val paradiseDependency = "org.scalamacros" % "paradise" % "2.0.0" cross CrossVersion.full

  lazy val generator = project.in(file("generator"))
    .settings(Twirl.settings:_*)
    .settings(
      libraryDependencies ++=Seq(
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.1.3",
        // AST generator dependencies
        "com.eed3si9n" %% "treehugger" % "0.3.0",
        // Macro generator dependencies
        "org.scala-lang" % "scala-reflect" % "2.10.4",
        paradiseDependency
      ),
      addCompilerPlugin(paradiseDependency)
    )

  lazy val sample = project.in(file("sample"))
    .dependsOn(generator)
    .settings(
      addCompilerPlugin(paradiseDependency)
    )

}