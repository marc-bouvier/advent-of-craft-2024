package communication

class SantaCommunicator(private val numberOfDaysToRest: Int) {


    fun composeMessage(
    message: SantaMessage,
    ): String {
     return composeMessage(message.reindeerName,message.currentLocation,message.numbersOfDaysForComingBack,message.numberOfDaysBeforeChristmas)
    }

    fun composeMessage(
        reindeerName: String,
        currentLocation: String,
        numbersOfDaysForComingBack: Int,
        numberOfDaysBeforeChristmas: Int
    ): String {
        val daysBeforeReturn = daysBeforeReturn(numbersOfDaysForComingBack, numberOfDaysBeforeChristmas)
        return "Dear $reindeerName, please return from $currentLocation in $daysBeforeReturn day(s) to be ready and rest before Christmas."
    }

    fun isOverdue(message: SantaMessage,logger: Logger): Boolean {
        return isOverdue(
            reindeerName = message.reindeerName,
            currentLocation = message.currentLocation,
            numbersOfDaysForComingBack = message.numbersOfDaysForComingBack,
            numberOfDaysBeforeChristmas = message.numberOfDaysBeforeChristmas,
            logger = logger
        )
    }
    fun isOverdue(
        reindeerName: String,
        currentLocation: String,
        numbersOfDaysForComingBack: Int,
        numberOfDaysBeforeChristmas: Int,
        logger: Logger
    ): Boolean {
        if (daysBeforeReturn(numbersOfDaysForComingBack, numberOfDaysBeforeChristmas) <= 0) {
            logger.log("Overdue for $reindeerName located $currentLocation.")
            return true
        }
        return false
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