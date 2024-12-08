package communication

class SantaCommunicator(private val numberOfDaysToRest: Int) {


    fun composeMessage(
        message: SantaMessage,
    ): String {
        val daysBeforeReturn = daysBeforeReturn(message.numbersOfDaysForComingBack, message.numberOfDaysBeforeChristmas)
        return "Dear ${message.reindeerName}, please return from ${message.currentLocation} in $daysBeforeReturn day(s) to be ready and rest before Christmas."
    }

    fun isOverdue(message: SantaMessage, logger: Logger): Boolean {
        return if (daysBeforeReturn(
                numbersOfDaysForComingBack = message.numbersOfDaysForComingBack,
                numberOfDaysBeforeChristmas = message.numberOfDaysBeforeChristmas
            ) <= 0
        ) {
            logger
                .log("Overdue for ${message.reindeerName} located ${message.currentLocation}.")
            true
        } else false
    }

    private fun daysBeforeReturn(numbersOfDaysForComingBack: Int, numberOfDaysBeforeChristmas: Int): Int {
        return numberOfDaysBeforeChristmas - numbersOfDaysForComingBack - numberOfDaysToRest
    }
}

data class SantaMessage(
    val reindeerName: String,
    val currentLocation: String,
    val numbersOfDaysForComingBack: Int,
    val numberOfDaysBeforeChristmas: Int,
) {

}