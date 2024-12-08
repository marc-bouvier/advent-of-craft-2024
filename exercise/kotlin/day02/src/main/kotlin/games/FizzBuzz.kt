package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

const val MIN = 1
const val MAX = 100

class FizzBuzz(vararg rules: Rule) {
    private val rules: List<Rule> = when {
        rules.isNotEmpty() -> listOf(*rules, identityRule())
        else -> listOf(*standardRules(), identityRule())
    }

    fun convert(input: Int): Option<String> = when {
        isOutOfRange(input) -> None
        else -> Some(convertSafely(input))
    }

    private fun convertSafely(input: Int): String = rules
        .first { rule -> rule.isValid(input) }
        .apply(input)

    private fun isOutOfRange(input: Int) = input < MIN || input > MAX

    class Rule(val divisor: Int, val rendering: (input: Int) -> String) {
        fun isValid(input: Int) = input % divisor == 0
        fun apply(input: Int) = rendering(input)
    }

    companion object {
        private fun standardRules() = arrayOf(
            Rule(divisor = 15, rendering = { "FizzBuzz" }),
            Rule(divisor = 5, rendering = { "Buzz" }),
            Rule(divisor = 3, rendering = { "Fizz" }),
        )

        fun convert(input: Int): Option<String> = FizzBuzz().convert(input)

        fun identityRule() = Rule(divisor = 1, rendering = { "$it" })
    }
}
