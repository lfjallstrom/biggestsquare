name := """wunderpahkina-vol6"""

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")