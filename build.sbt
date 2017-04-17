name := "selenium-functional-test"

version := "1.0"

scalaVersion := "2.12.1"

resolvers += Resolver.mavenLocal

isSnapshot := true

lazy val core = Project(id = "core", base = file("core")).settings(
  libraryDependencies ++= Seq(
    "org.seleniumhq.selenium" % "selenium-java" % "2.53.0" exclude("com.google.guava", "guava"),
    "com.google.guava" % "guava" % "18.0",
    "org.scalatest" %% "scalatest" % "3.0.1",
    "org.scalacheck" %% "scalacheck" % "1.13.4",
    "org.pegdown" % "pegdown" % "1.1.0",
    "com.relevantcodes" % "extentreports" % "2.41.2"
  )
)

lazy val test = Project(id = "test", base = file("test")).dependsOn(core).settings(
  testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-C", "com.kumarvikas1.scala.core.listeners.BaseListeners", "-h", "target/report")
)

lazy val root = project.in(file(".")).aggregate(core, test)
