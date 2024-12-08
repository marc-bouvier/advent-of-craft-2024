package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

const val MIN = 1
const val MAX = 100
private const val FIZZBUZZ = 15
private const val FIZZ = 3
private const val BUZZ = 5

object FizzBuzz {
    fun convert(input: Int): Option<String> = when {
        isOutOfRange(input) -> None
        else -> Some(convertSafely(input))
    }

    private fun convertSafely(input: Int): String {
        return listOf(
            FizzBuzzRule(FIZZBUZZ, { "FizzBuzz" }),
            FizzBuzzRule(BUZZ, { "Buzz" }),
            FizzBuzzRule(FIZZ, { "Fizz" }),
            FizzBuzzRule(divisor = 1, { "$it" })
        ).first { rule -> rule.isValid(input) }
            .apply(input)
    }

    private fun isOutOfRange(input: Int) = input < MIN || input > MAX

    class FizzBuzzRule(val divisor: Int, val rendering: (input: Int) -> String) {
        fun isValid(input: Int) = input % divisor == 0
        fun apply(input: Int) = rendering(input)
    }
}