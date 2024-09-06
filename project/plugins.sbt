val sbtTypelevelVersion = "0.7.3"

addSbtPlugin("org.typelevel" % "sbt-typelevel" % sbtTypelevelVersion)
addSbtPlugin("org.typelevel" % "sbt-typelevel-site" % sbtTypelevelVersion)
addSbtPlugin("org.typelevel" % "sbt-typelevel-scalafix" % sbtTypelevelVersion)
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.15.0")
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.15")

addSbtPlugin("org.jmotor.sbt" % "sbt-dependency-updates" % "1.2.7")
addSbtPlugin("com.timushev.sbt" % "sbt-rewarn" % "0.1.3")
