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

class SantaMessage(
    val numbersOfDaysForComingBack: DaysForComingBack,
    val numberOfDaysBeforeChristmas: DaysBeforeChristmas,
    val reindeer: Reindeer
) {

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
