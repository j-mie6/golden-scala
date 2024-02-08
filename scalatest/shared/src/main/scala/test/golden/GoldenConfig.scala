/*
 * Copyright 2024 Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package test.golden.scalatest

// this is probably core!
case class GoldenConfig(
    accept: Boolean
)
object GoldenConfig {
    val default = GoldenConfig(accept = false)
}
