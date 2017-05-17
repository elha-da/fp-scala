name := "fp-scala"
version := "1.0"

scalaVersion := "2.12.2"
scalacOptions += "-Ypartial-unification" // enable fix for SI-2712

resolvers += Resolver.sonatypeRepo("releases")

