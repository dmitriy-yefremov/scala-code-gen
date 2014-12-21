import sbt._
import Keys._

import twirl.sbt.TwirlPlugin._

object Build extends Build {

  /**
   * A dummy aggregator project.
   */
  lazy val root = project.in(file(".")).aggregate(generator, sample)

  lazy val generator = project.in(file("generator"))
    .settings(Twirl.settings:_*)

  lazy val sample = project.in(file("sample"))
    .dependsOn(generator)
    .settings(
      libraryDependencies ++= Seq(
        "org.scalariform" %% "scalariform" % "0.1.4"
      )
    )


}