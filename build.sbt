name := "akka-http-actuator"

version := "1.0.0"

scalaVersion := "2.12.2"

scalacOptions := Seq("-feature", "-deprecation", "-encoding", "utf8")

lazy val akkaHttpHal = ProjectRef(uri("https://github.com/marcuslange/akka-http-hal.git"), "akka-http-hal")

lazy val root = (project in file(".")).dependsOn(akkaHttpHal)

libraryDependencies ++= {
  val akkaV = "2.5.2"
  val akkaHttpV = "10.0.7"
  Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,

    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV % "test",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
}
