
val Scala213 = "2.13.12"
val Scala212 = "2.12.18"
val Scala3 = "3.3.1"

val mainBranch = "main"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(List(
    tlBaseVersion := "0.0",

    organization := "com.github.j-mie6",
    organizationName := "Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>",
    startYear := Some(2024),
    licenses := List("BSD-3-Clause" -> url("https://opensource.org/licenses/BSD-3-Clause")),

    crossScalaVersions := Seq(Scala213, Scala212, Scala3),
    scalaVersion := Scala213,

    // publish website from this branch
    tlCiReleaseBranches := Seq(mainBranch),
    tlCiScalafmtCheck := false,
    tlCiHeaderCheck := true,

    tlSitePublishBranch := Some(mainBranch),
))

lazy val root = tlCrossRootProject.aggregate(core)

lazy val commonSettings = Seq(
    headerLicenseStyle := HeaderLicenseStyle.SpdxSyntax,
    headerEmptyLine := false,

    resolvers ++= Opts.resolver.sonatypeOssReleases,
)

lazy val core = crossProject(JVMPlatform, JSPlatform, NativePlatform)
    .withoutSuffixFor(JVMPlatform)
    .crossType(CrossType.Full)
    .in(file("core"))
    .settings(
        name := "golden-scala",
        commonSettings,
        libraryDependencies ++= Seq(

        )
    )

lazy val docs = project.in(file("site"))
    .enablePlugins(TypelevelSitePlugin)
