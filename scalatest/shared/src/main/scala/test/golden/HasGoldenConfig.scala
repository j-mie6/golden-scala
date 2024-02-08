/*
 * Copyright 2024 Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package test.golden.scalatest

import org.scalatest.{Args, Status, Suite}

trait HasGoldenConfig extends Suite {
    private [golden] var goldenConfig: GoldenConfig = GoldenConfig.default

    abstract override def run(testName: Option[String], args: Args): Status = {
        val goldenAccept = args.configMap.getOptional[String]("golden.accept")
                                         .flatMap(_.toBooleanOption)
                                         .getOrElse(false)
        goldenConfig = goldenConfig.copy(accept = goldenAccept)
        super.run(testName, args)
    }
}
