import games.FizzBuzz
import games.MAX
import games.MIN
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.int
import io.kotest.property.forAll

val fizzBuzzStrings = listOf("Fizz", "Buzz", "FizzBuzz")
fun validStringsFor(x: Int): List<String> = fizzBuzzStrings + x.toString()

class FizzBuzzProperties : StringSpec({
    "parse return a valid string for numbers between 1 and 100" {
        forAll(Arb.int(MIN..MAX)) { x ->
            standardFizzBuzzConvert(x).isSome { result -> validStringsFor(x).contains(result) }
        }
    }

    "parse fail for numbers out of range" {
        forAll(Arb.int().filter { i -> i < MIN || i > MAX }) { x ->
            standardFizzBuzzConvert(x).isNone()
        }
    }
})

private fun standardFizzBuzzConvert(x: Int) = FizzBuzz.convert(x)