/*
 * Copyright 2024 Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package org.scalatest.golden

import org.scalatest.matchers.EqualMatchResult
import org.scalatest.matchers.MatchResult
import org.scalatest.matchers.Matcher

import scala.io.Source

trait GoldenMatchers extends HasGoldenConfig {
    def matchGolden(filename: String): Matcher[Any] = new Matcher[Any] {
        def apply(left: Any): MatchResult = {
            val leftStr = left.toString
            val rightStr = Source.fromFile(filename).mkString
            new EqualMatchResult(
                matches = leftStr == rightStr,
                rawFailureMessage = s"The contents of $filename did not match",
                rawNegatedFailureMessage = s"The contents of $filename matched",
                Vector(leftStr, rightStr),
                Vector(leftStr, rightStr),
            )
        }
    }
}
