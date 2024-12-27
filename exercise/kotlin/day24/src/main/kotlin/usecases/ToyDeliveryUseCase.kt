package usecases

import arrow.core.Either
import arrow.core.flatMap
import domain.Toy
import domain.ToyRepository
import domain.core.Error
import domain.core.Error.Companion.anError

class ToyDeliveryUseCase(private val r: ToyRepository) {
    fun handle(deliverToy: DeliverToy): Either<Error, Unit> {
        val findByName = r.findByName(deliverToy.desiredToy)
        return findByName
            .toEither { anError("Oops we have a problem... we have not built the toy: ${deliverToy.desiredToy}") }
            .flatMap { t ->
                var r = t.reduceStock()
                var e = r.isLeft()
                var v: Toy? = null
                if (e) {
                    v = t
                } else {
                    v = r.orNull()
                }
                if (null == v) {
                    // ça peut pas aller là
                } else {
                    this.r.save(v)
                }
                r
            }
            .map {Unit }
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
            this.r.save(v)
        }
        return r
    }


    private fun errorFor(deliverToy: DeliverToy): Error =
        anError("Oops we have a problem... we have not built the toy: ${deliverToy.desiredToy}")
}