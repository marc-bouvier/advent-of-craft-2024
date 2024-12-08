package communication

class SantaCommunicator(private val numberOfDaysToRest: Int) {


    fun composeMessage(
        message: SantaMessage,
    ): String {
        val daysBeforeReturn = daysBeforeReturn(message.numbersOfDaysForComingBack, message.numberOfDaysBeforeChristmas)
        return "Dear ${message.reindeerName}, please return from ${message.currentLocation} in $daysBeforeReturn day(s) to be ready and rest before Christmas."
    }

    fun isOverdue(message: SantaMessage, logger: Logger): Boolean {
        val numbersOfDaysForComingBack = message.numbersOfDaysForComingBack
        val numberOfDaysBeforeChristmas = message.numberOfDaysBeforeChristmas
        return if (daysBeforeReturn(
                numbersOfDaysForComingBack = numbersOfDaysForComingBack,
                numberOfDaysBeforeChristmas = numberOfDaysBeforeChristmas
            ) <= 0
        ) {
            logger
                .log("Overdue for ${message.reindeerName} located ${message.currentLocation}.")
            true
        } else false
    }

    private fun daysBeforeReturn(numbersOfDaysForComingBack: DaysForComingBack, numberOfDaysBeforeChristmas: DaysBeforeChristmas): Int {
        return numberOfDaysBeforeChristmas.asInt() - numbersOfDaysForComingBack.asInt() - numberOfDaysToRest
    }

    private fun daysBeforeReturn(numbersOfDaysForComingBack: Int, numberOfDaysBeforeChristmas: Int): Int {
        return numberOfDaysBeforeChristmas - numbersOfDaysForComingBack - numberOfDaysToRest
    }
}


class SantaMessage {
    val reindeerName: String
    val currentLocation: String
    val numbersOfDaysForComingBack: DaysForComingBack
    val numberOfDaysBeforeChristmas: DaysBeforeChristmas


    constructor(
        reindeerName: String,
        currentLocation: String,
        numbersOfDaysForComingBack: DaysForComingBack,
        numberOfDaysBeforeChristmas: DaysBeforeChristmas
    ) {
        this.reindeerName = reindeerName
        this.currentLocation = currentLocation
        this.numbersOfDaysForComingBack = numbersOfDaysForComingBack
        this.numberOfDaysBeforeChristmas = numberOfDaysBeforeChristmas
    }

}
class DaysBeforeChristmas(private val days: Int) {
    fun asInt(): Int {
        return days
    }
}
class DaysForComingBack(private val days: Int) {
    fun asInt(): Int {
        return days
    }
}
