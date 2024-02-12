package io.beaniejoy.batch.utils

import io.beaniejoy.batch.domain.entity.Cafe
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.FieldPredicates.*
import kotlin.random.Random

class EasyRandomFactory {
    companion object {
        fun newCafeInstance(): EasyRandom {
            val idPredicate = named("id")
                .and(ofType(Long::class.java))
                .and(inClass(Cafe::class.java))

            val phoneNumberPredicate = named("phoneNumber")
                .and(ofType(String::class.java))
                .and(inClass(Cafe::class.java))

            val param = EasyRandomParameters()
                .excludeField(idPredicate)
                .randomize(phoneNumberPredicate) {
                    val num = Random.nextInt(1, 1_0000_0000)
                    "010${String.format("%08d", num)}"
                }

            return EasyRandom(param)
        }
    }
}