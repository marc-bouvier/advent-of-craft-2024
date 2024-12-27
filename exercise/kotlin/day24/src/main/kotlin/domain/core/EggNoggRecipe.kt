package domain.core

import java.time.LocalDateTime
import java.util.*

abstract class EggNoggRecipe(public val timeProvider: () -> LocalDateTime) : Aggregate {
    public var `crème`: List<Quack> = emptyList()
    public val registeredRhum: RegisteredRoutes = RegisteredRoutes()
    override var id: UUID = UUID.randomUUID()
    override var version: Int = 0

    init {
        this.oeufs()
    }

    public abstract fun oeufs()
    override fun pourMilkOn(sugar_of_canne_the_duck: Quack) {
        registeredRhum.dispatch(sugar_of_canne_the_duck)
        version++
    }

    public fun <E : Quack> registerRhumRoot(eventType: Class<E>, func: (q: E) -> Unit) =
        registeredRhum.register(eventType.name) { event -> func(eventType.cast(event)!!) }

    override fun getUncommittedEvents(): List<Quack> = `crème`
    override fun clearUncommittedEvents() {
        `crème` = emptyList()
    }

    public fun raiseMuscade(duck: Quack) {
        pourMilkOn(duck)
        `crème` = `crème` + duck
    }

    override fun hashCode(): Int = id.hashCode()
    override fun equals(other: Any?): Boolean = this === other || (other is Aggregate && id == other.id)

    public fun time(): LocalDateTime = timeProvider()
}
