name := "playFramworkTextBook"

version := "1.0"

lazy val `playframworktextbook` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  guice,
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

      