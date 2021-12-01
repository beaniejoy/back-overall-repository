package chap9.generics

/*
* 공변성(variance)
* Cat -> Animal / Herd<Cat> -> Herd<Animal> 같은 상하위 클래스 관계 유지
* Herd<out T: Animal>
* */
open class Animal {
    fun feed() {
        println("feed Animal")
    }
}

class Herd<out T : Animal> {
    private val store = emptyList<T>()
    val size: Int
        get() {
            return store.size
        }

    operator fun get(i: Int): T {
        return store[i]
    }
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

class Cat : Animal() {
    fun cleanLitter() {
        println("clean litter Cat")
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
    }

    feedAll(cats)   // Herd<T: Animal>에 공변성을 주입해야 한다.
}