/*
 * Copyright 2024 Golden Scala Contributors <https://github.com/j-mie6/golden-scala/graphs/contributors>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package org.scalatest.golden

import org.scalatest.matchers.EqualMatchResult
import org.scalatest.matchers.MatchResult
import org.scalatest.matchers.Matcher

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import scala.io.Source
import scala.util.Failure
import scala.util.Success
import scala.util.Try

// TODO: actually, it would be good if the status' of the tests can change
// depending on mode:
//    in ACCEPT, tests that didn't match and were overwritten should be IGNORED
//    in GENERATE, tests that were made now should be PENDING?
trait GoldenMatchers extends HasGoldenConfig {
    def matchGolden(filename: String): Matcher[Any] = new Matcher[Any] {
        private def compare(left: String, right: String) = new EqualMatchResult(
            matches = left == right,
            rawFailureMessage = s"The contents of $filename did not match",
            rawNegatedFailureMessage = s"The contents of $filename matched",
            Vector(left, right),
            Vector(left, right),
        )

        private def generate(left: String) = {
            val res = Try {
                val buff = new BufferedWriter(new FileWriter(new File(filename)))
                buff.write(left)
                buff.close()
            }
            MatchResult(
                matches = res.isSuccess,
                rawFailureMessage = s"The golden test $filename could not be generated",
                rawNegatedFailureMessage = s"The golden test $filename was generated",
            )
        }

        def apply(left: Any): MatchResult = {
            val leftStr = left.toString
            (goldenConfig.mode, Try(Source.fromFile(filename).mkString)) match {
                case (GoldenGenerate | GoldenCheck, Success(rightStr)) => compare(leftStr, rightStr)
                case (GoldenAccept, _) => generate(leftStr)
                case (GoldenGenerate, Failure(_)) => generate(leftStr)
                case (GoldenCheck, Failure(_)) =>
                    MatchResult(
                        matches = false,
                        rawFailureMessage = s"The golden test $filename does not exist",
                        rawNegatedFailureMessage = s"The golden test $filename existed?",
                    )
            }
        }
    }
}
