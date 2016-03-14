name := "akka-http"

version := "1.0"

scalaVersion := "2.11.8"

val akkaVersion = "2.4.2"
val sprayVersion = "1.3.2"
val accordVersion = "0.5"
val scalaLoggingVersion = "3.1.0"

lazy val commonDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-core-experimental" % "2.0.3",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.0.3",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.0.3",
  "io.spray" %% "spray-can" % sprayVersion,
  "io.spray" %% "spray-json" % sprayVersion,
  "io.spray" %% "spray-httpx" % sprayVersion,
  "com.wix" %% "accord-core" % accordVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion
)

libraryDependencies ++= commonDependencies