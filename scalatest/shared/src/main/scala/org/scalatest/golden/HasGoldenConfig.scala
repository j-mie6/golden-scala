/*
 * Copyright 2024 Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package org.scalatest.golden

import org.scalatest.Args
import org.scalatest.Status
import org.scalatest.Suite

trait HasGoldenConfig extends Suite {
    private [golden] var goldenConfig: GoldenConfig = GoldenConfig.default

    abstract override def run(testName: Option[String], args: Args): Status = {
        val goldenMode = args.configMap.getOptional[String]("golden.mode") match {
            case Some("accept") => GoldenAccept
            //case Some("check") => GoldenCheck
            case Some("generate") => GoldenGenerate
            case _ => GoldenCheck
        }
        // TODO: diffing modes
        goldenConfig = goldenConfig.copy(mode = goldenMode)
        super.run(testName, args)
    }
}
