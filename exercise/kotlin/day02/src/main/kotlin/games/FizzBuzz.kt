package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

const val MIN = 1
const val MAX = 100
private const val FIZZBUZZ = 15
private const val FIZZ = 3
private const val BUZZ = 5

private val FIZZ_BUZZ_RULE = FizzBuzz.FizzBuzzRule(FIZZBUZZ, { "FizzBuzz" })
private val BUZZ_RULE = FizzBuzz.FizzBuzzRule(BUZZ, { "Buzz" })
private val FIZZ_RULE = FizzBuzz.FizzBuzzRule(FIZZ, { "Fizz" })
private val DEFAULT_RULE = FizzBuzz.FizzBuzzRule(1, { "$it" })

object FizzBuzz {
    fun convert(input: Int): Option<String> = when {
        isOutOfRange(input) -> None
        else -> Some(convertSafely(input))
    }


    private fun convertSafely(input: Int): String {
        return listOf(
            FIZZ_BUZZ_RULE,
            BUZZ_RULE,
            FIZZ_RULE,
            DEFAULT_RULE
        ).first { rule -> rule.isValid(input) }
            .apply(input)
    }

    private fun isOutOfRange(input: Int) = input < MIN || input > MAX

    class FizzBuzzRule(val divisor: Int, val rendering: (input: Int) -> String) {
        fun isValid(input: Int) = input % divisor == 0
        fun apply(input: Int) = rendering(input)
    }
}