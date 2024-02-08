package test.golden.scalatest

// this is probably core!
case class GoldenConfig(
    accept: Boolean
)
object GoldenConfig {
    val default = GoldenConfig(accept = false)
}
