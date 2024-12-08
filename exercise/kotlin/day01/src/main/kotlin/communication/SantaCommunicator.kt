package communication

class SantaCommunicator(private val numberOfDaysToRest: Int) {


    fun composeMessage(
        message: SantaMessage,
    ): String {
        val daysBeforeReturn = daysBeforeReturn(message.numbersOfDaysForComingBack, message.numberOfDaysBeforeChristmas)
        val reindeer = message.reindeer
        return "Dear ${reindeer.reindeerName}, please return from ${reindeer.currentLocation} in $daysBeforeReturn day(s) to be ready and rest before Christmas."
    }

    fun isOverdue(message: SantaMessage, logger: Logger): Boolean {
        return if (daysBeforeReturn(
                message.numbersOfDaysForComingBack, message.numberOfDaysBeforeChristmas
            ) <= 0
        ) {
            val reindeer = message.reindeer
            logger
                .log("Overdue for ${reindeer.reindeerName} located ${reindeer.currentLocation}.")
            true
        } else false
    }

    private fun daysBeforeReturn(numbersOfDaysForComingBack: DaysForComingBack, numberOfDaysBeforeChristmas: DaysBeforeChristmas): Int {
        return numberOfDaysBeforeChristmas.asInt() - numbersOfDaysForComingBack.asInt() - numberOfDaysToRest
    }

}


class Reindeer(val reindeerName: String,
               val currentLocation: String)

class SantaMessage {
    val reindeerName: String
    val reindeer: Reindeer
    val currentLocation: String
    val numbersOfDaysForComingBack: DaysForComingBack
    val numberOfDaysBeforeChristmas: DaysBeforeChristmas

    constructor(
        numbersOfDaysForComingBack: DaysForComingBack,
        numberOfDaysBeforeChristmas: DaysBeforeChristmas,
        reindeer1: Reindeer
    ) {
        this.reindeer = reindeer1
        this.reindeerName = reindeer1.reindeerName
        this.currentLocation = reindeer1.currentLocation
        this.numbersOfDaysForComingBack = numbersOfDaysForComingBack
        this.numberOfDaysBeforeChristmas = numberOfDaysBeforeChristmas
    }

    constructor(
        reindeerName: String,
        currentLocation: String,
        numbersOfDaysForComingBack: DaysForComingBack,
        numberOfDaysBeforeChristmas: DaysBeforeChristmas,
        reindeer1: Reindeer
    ) {
        this.reindeer = reindeer1
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
