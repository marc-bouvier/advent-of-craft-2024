package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

const val MIN = 1
const val MAX = 100

class FizzBuzz(vararg rules: Rule) {
    val rules: List<Rule>

    init {

        this.rules = when {
            rules.isNotEmpty() -> listOf(*rules, identityRule())
            else -> listOf(*standardRules(), identityRule())
        }
    }

    fun convert(input: Int): Option<String> = when {
        isOutOfRange(input) -> None
        else -> Some(convertSafely(input))
    }

    private fun standardRules() = arrayOf(
        Rule(15, { "FizzBuzz" }),
        Rule(5, { "Buzz" }),
        Rule(3, { "Fizz" }),
    )

    private fun convertSafely(input: Int): String = rules
        .first { rule -> rule.isValid(input) }
        .apply(input)

    private fun isOutOfRange(input: Int) = input < MIN || input > MAX

    class Rule(val divisor: Int, val rendering: (input: Int) -> String) {
        fun isValid(input: Int) = input % divisor == 0
        fun apply(input: Int) = rendering(input)
    }


    companion object {

        fun convert(input: Int): Option<String> = FizzBuzz().convert(input)

        fun identityRule() = Rule(1, { "$it" })
    }
}
