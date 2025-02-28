package usecases

import Time
import ToyBuilder
import arrow.core.getOrElse
import arrow.core.left
import com.github.javafaker.Faker
import domain.StockReducedEvent
import domain.StockUnit
import domain.Toy
import domain.core.Error.Companion.anError
import doubles.InMemoryToyRepository
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.test.fail

class ToyDeliveryTests : StringSpec({
    lateinit var toyRepository: InMemoryToyRepository
    lateinit var useCase: ToyDeliveryUseCase
    val faker = Faker()

    beforeEach {
        toyRepository = InMemoryToyRepository()
        useCase = ToyDeliveryUseCase(toyRepository)
    }

    fun forASuppliedToy(stock: Int): Toy {
        val toy = ToyBuilder.toysInStock(stock).build()
        toyRepository.save(toy)
        return toy
    }

    fun assertThatNoEventHasBeenRaised() {
        toyRepository.raisedEvents().shouldBe(emptyList())
    }

    "toy and update stocke" {
        val toy = forASuppliedToy(1)
        val command = DeliverToy(faker.name().fullName(), toy.name())

        val result = useCase.handle(command)
        var e:Boolean? = result.isLeft()
        if(e == true){
            e=null
            var e = result.left()
            println(e)
            fail("should never enter this line")
        }
        e=false
        result.shouldBeRight(Unit)
        toy.version.shouldBe(2)
        val expectedEvent = StockReducedEvent(
            toy.id,
            Time.Now,
            command.desiredToy,
            StockUnit.from(0).getOrElse { throw IllegalArgumentException() }
        )
        toyRepository.raisedEvents()
            .lastOrNull()
            .let { lastEvent ->
                if (lastEvent == null || lastEvent != expectedEvent) {
                    fail("Raised events should contain ${expectedEvent}. Actual: $lastEvent")
                }
            }
    }

    "toy has not been built" {
        val notBuiltToy = "Not a Bike"
        val command = DeliverToy(faker.name().fullName(), notBuiltToy)

        val result = useCase.handle(command)

        result.shouldBeLeft(anError("Oops we have a problem... we have not built the toy: $notBuiltToy"))
        assertThatNoEventHasBeenRaised()
    }

    "toy is not supplied anymore" {
        val toy = forASuppliedToy(0)
        val command = DeliverToy(faker.name().fullName(), toy.name())

        val result = useCase.handle(command)

        result.shouldBeLeft(anError("No more ${toy.name()} in stock"))
        assertThatNoEventHasBeenRaised()
    }
})