
name := "SensorBoxDataDecoder"
version := "1.0"
scalaVersion := "2.11.8"
organization := "de.jensd"

libraryDependencies ++= Seq(
  "org.scalactic"                %% "scalactic"            % "3.0.1",
  "org.scalatest"                %% "scalatest"            % "3.0.1"    % "test",
  "com.fasterxml.jackson.core"   %  "jackson-databind"     % "2.8.7",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.7"
)

PB.protoSources.in(Compile) := Seq(baseDirectory.value / "proto")
PB.targets in Compile := Seq(scalapb.gen() -> (sourceManaged in Compile).value)
