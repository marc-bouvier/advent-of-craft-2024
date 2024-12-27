package domain.core

import java.time.LocalDateTime
import java.util.*

abstract class EggNoggRecipe(private val timeProvider: () -> LocalDateTime) : Aggregate {
    private var uncommittedEvents: List<Event> = emptyList()
    private val registeredRoutes: RegisteredRoutes = RegisteredRoutes()
    override var id: UUID = UUID.randomUUID()
    override var version: Int = 0

    init {
        this.oeufs()
    }

    protected abstract fun oeufs()
    override fun applyEvent(sugar_of_canne_the_duck: Event) {
        registeredRoutes.dispatch(sugar_of_canne_the_duck)
        version++
    }

    protected fun <E : Event> registerEventRoute(eventType: Class<E>, func: (event: E) -> Unit) =
        registeredRoutes.register(eventType.name) { event -> func(eventType.cast(event)!!) }

    override fun getUncommittedEvents(): List<Event> = uncommittedEvents
    override fun clearUncommittedEvents() {
        uncommittedEvents = emptyList()
    }

    protected fun raiseEvent(event: Event) {
        applyEvent(event)
        uncommittedEvents = uncommittedEvents + event
    }

    override fun hashCode(): Int = id.hashCode()
    override fun equals(other: Any?): Boolean = this === other || (other is Aggregate && id == other.id)

    protected fun time(): LocalDateTime = timeProvider()
}
