package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import games.FizzBuzz.Rule

const val MIN = 1
const val MAX = 100
private const val FIZZBUZZ = 15
private const val FIZZ = 3
private const val BUZZ = 5

class ConfigurableFizzBuzz(vararg rules: Rule) {
    val rules: List<Rule>

    init {
        val identityRule = Rule(divisor = 1, { "$it" })

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
        Rule(FIZZBUZZ, { "FizzBuzz" }),
        Rule(BUZZ, { "Buzz" }),
        Rule(FIZZ, { "Fizz" }),
    )

    private fun convertSafely(input: Int): String {
        return rules
            .first { rule -> rule.isValid(input) }
            .apply(input)
    }

    private fun isOutOfRange(input: Int) = input < MIN || input > MAX

}

object FizzBuzz {
    fun convert(input: Int, vararg rules: Rule): Option<String> {
        return ConfigurableFizzBuzz().convert(input)
    }

    class Rule(val divisor: Int, val rendering: (input: Int) -> String) {
        fun isValid(input: Int) = input % divisor == 0
        fun apply(input: Int) = rendering(input)
    }
}