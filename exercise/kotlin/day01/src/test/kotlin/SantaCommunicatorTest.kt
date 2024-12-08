package communication

import doubles.TestLogger
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SantaCommunicatorTest : DescribeSpec({
    val numberOfDaysToRest = 2
    val numberOfDayBeforeChristmas = 24
    val logger = TestLogger()
    lateinit var communicator: SantaCommunicator

    beforeEach {
        communicator = SantaCommunicator(numberOfDaysToRest)
    }

    describe("composeMessage") {
        it("should compose correct message") {
            communicator
                .composeMessage(
                    SantaMessage(
                        "Dasher",
                        "North Pole",
                        DaysForComingBack(5),
                        DaysBeforeChristmas( numberOfDayBeforeChristmas),
                        Reindeer("Dasher", "North Pole")
                    )
                ) shouldBe "Dear Dasher, please return from North Pole in 17 day(s) to be ready and rest before Christmas."
        }
    }

    describe("isOverdue") {
        it("should detect overdue reindeer") {
            val santaMessage = SantaMessage(
                "Dasher",
                "North Pole",
                DaysForComingBack(numberOfDayBeforeChristmas),
                DaysBeforeChristmas(numberOfDayBeforeChristmas),
 Reindeer("Dasher", "North Pole"),
            )
            val overdue = communicator.isOverdue(
                santaMessage,
                logger = logger
            )

            overdue shouldBe true
            logger.getLog() shouldBe "Overdue for Dasher located North Pole."
        }

        it("should return false when not overdue") {
            val santaMessage = SantaMessage(
                "Dasher",
                "North Pole",
                DaysForComingBack(numberOfDayBeforeChristmas - numberOfDaysToRest - 1),
                DaysBeforeChristmas(numberOfDayBeforeChristmas),
 Reindeer("Dasher", "North Pole"),
            )
            communicator.isOverdue(
                message = santaMessage,
                logger = logger
            ) shouldBe false
        }
    }
})

