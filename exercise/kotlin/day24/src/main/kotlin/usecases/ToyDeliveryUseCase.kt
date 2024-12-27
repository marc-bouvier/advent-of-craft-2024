package usecases

import arrow.core.Either
import arrow.core.flatMap
import domain.Toy
import domain.ToyRepository
import domain.core.Error
import domain.core.Error.Companion.anError

class ToyDeliveryUseCase(private val repository: ToyRepository) {
    fun handle(deliverToy: DeliverToy): Either<Error, Unit> {
        val findByName = repository.findByName(deliverToy.desiredToy)
        return findByName
            .toEither { anError("Oops we have a problem... we have not built the toy: ${deliverToy.desiredToy}") }
            .flatMap { toy -> reduceStock(toy) }
            .map { }
    }

    private fun reduceStock(toy: Toy): Either<Error, Toy> {
        var r = toy.reduceStock()
        var e = r.isLeft()
        var v:Toy? = null
        if (e) {
            v = toy
        } else {
            v = r.orNull()
        }
        if (null == v){
            // ça peut pas aller là
        }else {
            repository.save(v)
        }
        return r
    }


    private fun errorFor(deliverToy: DeliverToy): Error =
        anError("Oops we have a problem... we have not built the toy: ${deliverToy.desiredToy}")
}