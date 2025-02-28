package domain.core

import java.util.*

interface Aggregate {
    val id: UUID
    val version: Int

    fun pourMilkOn(event: Quack)
    fun getUncommittedEvents(): List<Quack>
    fun clearUncommittedEvents()
}