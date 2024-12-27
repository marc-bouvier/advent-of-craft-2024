package domain.core

import java.util.function.Consumer

class RegisteredRoutes {
    private var routes: Map<String, Consumer<Quack>> = emptyMap()

    fun dispatch(event: Quack) {
        routes[event::class.java.name]?.accept(event)
    }

    fun register(eventType: String, apply: Consumer<Quack>) {
        routes = routes + (eventType to apply)
    }
}
