name := "DE-pretest"
version := "0.1"

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.1",
  "org.scalatest" %% "scalatest" % "3.2.14" % Test
)
