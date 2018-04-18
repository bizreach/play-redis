name := "play-modules-redis"
organization := "jp.co.bizreach"

crossScalaVersions := Seq("2.12.4")
scalaVersion := "2.12.4"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-encoding", "UTF-8")
scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
  "com.typesafe.play"         %% "play"               % "2.6.12"    % "provided",
  "com.typesafe.play"         %% "play-cache"         % "2.6.12",
  "com.typesafe.play"         %% "play-test"          % "2.6.12"    % "test",
  "com.typesafe.play"         %% "play-specs2"        % "2.6.12"    % "test",
  "biz.source_code"           %  "base64coder"        % "2010-12-19",
  "redis.clients"             %  "jedis"              % "2.9.0"
)

pomExtra := {
  <scm>
    <url>https://github.com/bizreach/play-redis</url>
    <connection>scm:git:git@github.com:bizreach/play-redis.git</connection>
  </scm>
  <developers>
    <developer>
      <id>typesafe</id>
      <name>Typesafe</name>
      <url>https://typesafe.com</url>
    </developer>
    <developer>
      <id>takezoe</id>
      <name>Naoki Takezoe</name>
      <url>https://github.com/takezoe</url>
    </developer>
  </developers>
}
pomIncludeRepository := { _ => false }
homepage := Some(url(s"https://github.com/bizreach/play-redis"))
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))
publishTo := sonatypePublishTo.value

sonatypeProfileName := "jp.co.bizreach"
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseTagName := s"redis-${(version in ThisBuild).value}"
releaseCrossBuild := true

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepCommand("sonatypeRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
