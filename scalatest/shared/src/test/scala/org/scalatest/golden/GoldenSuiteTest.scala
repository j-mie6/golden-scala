/*
 * Copyright 2024 Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package org.scalatest.golden

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

// this is soooo meta lol
class GoldenSuiteTest extends AnyFlatSpec with GoldenMatchers {
    "golden tests" should "register" in {
        """Hello world,
          |This is a file!
          |
          |It spans many lines.
          |""".stripMargin should matchGolden("golden/helloworld.golden")
    }
}
