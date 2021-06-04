lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "2.12.8",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Ypartial-unification"
  ),
  addCompilerPlugin("org.typelevel" % "kind-projector" % "0.11.3" cross CrossVersion.full),
  resolvers += Resolver.sonatypeRepo("releases"),
  libraryDependencies += "org.typelevel" %% "cats-core" % "2.3.0"
)


lazy val scalaCatExecise = project.in(file("."))
  .settings(moduleName := "scalaCatExecise")
  .settings(baseSettings: _*)
  .aggregate(core, slides)
  .dependsOn(core, slides)

lazy val core = project
  .settings(moduleName := "scalaCatExecise-core")
  .settings(baseSettings: _*)


lazy val slides = project
  .settings(moduleName := "scalaCatExecise-slides")
  .settings(baseSettings: _*)
  .settings(
    tutSourceDirectory := baseDirectory.value / "tut",
    tutTargetDirectory := baseDirectory.value / "../docs",
    watchSources ++= (tutSourceDirectory.value ** "*.html").get
  ).dependsOn(core)
  .enablePlugins(TutPlugin)