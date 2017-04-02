import sbt.Keys.libraryDependencies

name := "sensorbox-data-decoder"
version := "1.0"
scalaVersion := "2.11.8"
organization := "de.jensd"

resolvers += Resolver.jcenterRepo
resolvers += Resolver.mavenLocal

mainClass:=Some("de.jensd.demo.Publish")

libraryDependencies ++= Seq(
  "org.scalactic"                %% "scalactic"                       % "3.0.1",
  "org.scalatest"                %% "scalatest"                       % "3.0.1"    % "test",
  "com.fasterxml.jackson.core"   %  "jackson-databind"                % "2.8.7",
  "com.fasterxml.jackson.module" %% "jackson-module-scala"            % "2.8.7",
  "org.eclipse.paho"             %  "org.eclipse.paho.client.mqttv3"  % "1.1.0",
  "de.jensd"                     %  "mqttfx-payload-decoders"         % "1.3.1"
)

PB.protoSources.in(Compile) := Seq(baseDirectory.value / "proto")
PB.targets in Compile := Seq(scalapb.gen() -> (sourceManaged in Compile).value)
