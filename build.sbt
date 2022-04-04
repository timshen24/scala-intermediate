ThisBuild / version := "0.1.0-SNAPSHOT"

//ThisBuild / scalaVersion := "2.13.8"
ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-intermediate"
  )

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-encoding",
  "UTF-8",
  "-language:existentials",
  "-language:postfixOps",
  "-unchecked",
  "-Ywarn-value-discard"
)