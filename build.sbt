name := "csv-demo-scala"

version := "1.0"

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.6.0",
  "com.novocode" % "junit-interface" % "latest.release" % Test,
  "org.scalatest" %% "scalatest" % "latest.release" % Test,
  "com.lihaoyi" %% "ammonite-ops" % "1.0.0"
)
