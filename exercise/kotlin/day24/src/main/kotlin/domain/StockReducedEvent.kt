package domain

import domain.core.Quack
import java.time.LocalDateTime
import java.util.*

data class StockReducedEvent(
    override val id: UUID,
    override val date: LocalDateTime,
    val toyName: String,
    val newStock: StockUnit
) : Quack
