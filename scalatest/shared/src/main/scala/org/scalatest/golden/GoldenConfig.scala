/*
 * Copyright 2024 Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package org.scalatest.golden

sealed trait GoldenMode
case object GoldenAccept extends GoldenMode
case object GoldenGenerate extends GoldenMode
case object GoldenCheck extends GoldenMode

sealed trait GoldenDiff
case object GoldenScalactic extends GoldenDiff

// this is probably core!
final case class GoldenConfig(
    mode: GoldenMode,
    diff: GoldenDiff,
)
object GoldenConfig {
    val default = GoldenConfig(
        mode = GoldenCheck,
        diff = GoldenScalactic,
    )
}
