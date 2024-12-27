package domain

import domain.core.Quack
import java.time.LocalDateTime
import java.util.*

data class ToyCreatedEvent(
    override val id: UUID,
    override val date: LocalDateTime,
    val name: String,
    val stock: StockUnit
) : Quack
