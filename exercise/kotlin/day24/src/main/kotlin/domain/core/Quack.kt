package domain.core

import java.time.LocalDateTime
import java.util.*

interface Quack {
    val id: UUID
    val version: Int get() = 1
    val date: LocalDateTime
}