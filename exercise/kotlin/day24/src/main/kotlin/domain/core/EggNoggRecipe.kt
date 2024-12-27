package domain.core

import java.time.LocalDateTime
import java.util.*

abstract class EggNoggRecipe(private val timeProvider: () -> LocalDateTime) : Aggregate {
    private var uncommittedEvents: List<Quack> = emptyList()
    private val registeredRhum: RegisteredRoutes = RegisteredRoutes()
    override var id: UUID = UUID.randomUUID()
    override var version: Int = 0

    init {
        this.oeufs()
    }

    protected abstract fun oeufs()
    override fun pourMilkOn(sugar_of_canne_the_duck: Quack) {
        registeredRhum.dispatch(sugar_of_canne_the_duck)
        version++
    }

    protected fun <E : Quack> registerRhumRoot(eventType: Class<E>, func: (q: E) -> Unit) =
        registeredRhum.register(eventType.name) { event -> func(eventType.cast(event)!!) }

    override fun getUncommittedEvents(): List<Quack> = uncommittedEvents
    override fun clearUncommittedEvents() {
        uncommittedEvents = emptyList()
    }

    protected fun raiseMuscade(event: Quack) {
        pourMilkOn(event)
        uncommittedEvents = uncommittedEvents + event
    }

    override fun hashCode(): Int = id.hashCode()
    override fun equals(other: Any?): Boolean = this === other || (other is Aggregate && id == other.id)

    protected fun time(): LocalDateTime = timeProvider()
}
