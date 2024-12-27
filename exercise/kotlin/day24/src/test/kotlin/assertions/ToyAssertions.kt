package assertions

import domain.Toy
import domain.core.Quack
import doubles.InMemoryToyRepository
import kotlin.test.fail

fun Toy.shouldHaveRaisedEvent(repository: InMemoryToyRepository, expectedEvent: Quack) =
    repository.raisedEvents()
        .lastOrNull()
        .let { lastEvent ->
            if (lastEvent == null || lastEvent != expectedEvent) {
                fail("Raised events should contain $expectedEvent. Actual: $lastEvent")
            }
        }