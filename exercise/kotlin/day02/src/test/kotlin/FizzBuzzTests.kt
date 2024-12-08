import games.FizzBuzz
import io.kotest.assertions.arrow.core.shouldBeNone
import io.kotest.assertions.arrow.core.shouldBeSome
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData

class FizzBuzzTests : FunSpec({
    context("returns its numbers representation") {
        withData(
            ValidInput(1, "1"),
            ValidInput(67, "67"),
            ValidInput(7, "Whizz"),
            ValidInput(14, "Whizz"),
            ValidInput(21, "Whizz"),
            ValidInput(28, "Whizz"),
            ValidInput(35, "Whizz"),
            ValidInput(82, "82"),
            ValidInput(3, "Fizz"),
            ValidInput(66, "Bang"),
            ValidInput(99, "Bang"),
            ValidInput(5, "Buzz"),
            ValidInput(50, "Buzz"),
            ValidInput(85, "Buzz"),
            ValidInput(15, "FizzBuzz"),
            ValidInput(30, "FizzBuzz"),
            ValidInput(45, "FizzBuzz")
        ) { (input, expectedResult) ->
            fizzBuzzConvert(input).shouldBeSome(expectedResult)
        }
    }

    context("fails for numbers out of range") {
        withData(0, -1, 101) { x ->
            standardFizzBuzzConvert(x).shouldBeNone()
        }
    }
})

private fun standardFizzBuzzConvert(input: Int) = FizzBuzz.convert(input)

private fun fizzBuzzConvert(input: Int) = FizzBuzz(
    FizzBuzz.Rule(15, { "FizzBuzz" }),
    FizzBuzz.Rule(11, { "Bang" }),
    FizzBuzz.Rule(7, { "Whizz" }),
    FizzBuzz.Rule(5, { "Buzz" }),
    FizzBuzz.Rule(3, { "Fizz" }),
    FizzBuzz.identityRule()
)
    .convert(input)

data class ValidInput(val input: Int, val expectedResult: String)