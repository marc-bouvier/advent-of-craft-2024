package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

const val MIN = 1
const val MAX = 100
private const val FIZZBUZZ = 15
private const val FIZZ = 3
private const val BUZZ = 5

private val FIZZ_BUZZ_RULE = FizzBuzz.FizzBuzzRule(FIZZBUZZ,{"FizzBuzz"})
private val BUZZ_RULE = FizzBuzz.FizzBuzzRule(BUZZ,{"Buzz"})

object FizzBuzz {
    fun convert(input: Int): Option<String> = when {
        isOutOfRange(input) -> None
        else -> Some(convertSafely(input))
    }


    private fun convertSafely(input: Int): String = when {
        FIZZ_BUZZ_RULE.isValid(input) -> FIZZ_BUZZ_RULE.apply(input)
        BUZZ_RULE.isValid(input) -> BUZZ_RULE.apply(input)
        `is`(FIZZ, input) -> "Fizz"
        `is`(BUZZ, input) -> "Buzz"
        else -> input.toString()
    }

    private fun `is`(divisor: Int, input: Int): Boolean = input % divisor == 0
    private fun isOutOfRange(input: Int) = input < MIN || input > MAX

    class FizzBuzzRule(val divisor: Int, val rendering: (input: Int) -> String) {
        fun isValid(input: Int) = input % divisor == 0
        fun apply(input: Int) = rendering(input)
    }
}