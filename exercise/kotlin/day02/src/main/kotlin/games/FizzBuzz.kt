package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import games.FizzBuzz.FizzBuzzRule

const val MIN = 1
const val MAX = 100
private const val FIZZBUZZ = 15
private const val FIZZ = 3
private const val BUZZ = 5

class ConfigurableFizzBuzz(vararg rules: FizzBuzzRule) {
    val rules: List<FizzBuzzRule>

    init {
        val identityRule = FizzBuzzRule(divisor = 1, { "$it" })

        this.rules = when {
            rules.isNotEmpty() -> listOf(*rules, identityRule)
            else -> listOf(*standardRules(), identityRule)
        }
    }

    fun convert(input: Int): Option<String> = when {
        isOutOfRange(input) -> None
        else -> Some(convertSafely(input))
    }

    private fun standardRules() = arrayOf(
        FizzBuzzRule(FIZZBUZZ, { "FizzBuzz" }),
        FizzBuzzRule(BUZZ, { "Buzz" }),
        FizzBuzzRule(FIZZ, { "Fizz" }),
    )

    private fun convertSafely(input: Int): String {
        return rules
            .first { rule -> rule.isValid(input) }
            .apply(input)
    }

    private fun isOutOfRange(input: Int) = input < MIN || input > MAX

}

object FizzBuzz {
    fun convert(input: Int, vararg rules: FizzBuzzRule): Option<String> {
        return ConfigurableFizzBuzz().convert(input)
    }

    class FizzBuzzRule(val divisor: Int, val rendering: (input: Int) -> String) {
        fun isValid(input: Int) = input % divisor == 0
        fun apply(input: Int) = rendering(input)
    }
}